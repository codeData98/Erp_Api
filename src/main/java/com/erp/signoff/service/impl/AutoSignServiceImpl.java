package com.erp.signoff.service.impl;

import com.erp.signoff.common.BusinessException;
import com.erp.signoff.dto.SignCursorRow;
import com.erp.signoff.entity.*;
import com.erp.signoff.mapper.*;
import com.erp.signoff.service.AutoSignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动签收 Service 实现（骨架，业务逻辑待实现）。
 *
 * <p>实现建议参考 PL/SQL 存储过程 P_PRODUCE_SIGN_DETAIL_AUTO_PS 的步骤：</p>
 * <ol>
 *   <li>取 sf_proc_rcm 的 vend_no；再按 vend_no 查 sys_org 得出货组织 org_id（查不到直接返回）。
 *       按收货 org_id 查 sys_org 取 cust_id。</li>
 *   <li>生成 header_id（序列/发号器）、sign_no（mark + 'PSD' + YYMMDD + 4 位当日流水）。</li>
 *   <li>插入 ps_sign_m（status=3 已审核，is_sales='Y'）。</li>
 *   <li>执行主追溯游标（sf_proc_rcm → sf_proc_item → ps_proc_m → ps_proc_item → ps_proc_d → ps_proc_rcm），
 *       过滤未签收数量 &gt; 0。注意 PI.PROC_SEQ = PI2.PROC_SEQ 必须严格对应，否则会串行。</li>
 *   <li>逐行：算 line_id → 写入 ap_inside_auto_rec（独立事务提交，用于留痕）→
 *       写 ps_sign_d / ps_sign_s（尺码来源 sf_proc_rcsize；无尺码则写 size_no='*'）。
 *       单行异常写入 ap_inside_auto_rec.error_text 后继续下一行。</li>
 *   <li>若一行都没成功，删除已插入的 ps_sign_m 头。</li>
 * </ol>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AutoSignServiceImpl implements AutoSignService {


    private final SfProcRcmMapper sfProcRcmMapper;
    private final SysOrgMapper sysOrgMapper;
    private final PsSignMMapper psSignMMapper;
    private final PsSignDMapper psSignDMapper;
    private final PsSignSMapper psSignSMapper;

//    @RequiredArgsConstructor 注解等价于构造器注入 会为final 和 @NUll字段生成构造器注入
//    public AutoSignServiceImpl(SfProcRcmMapper sfProcRcmMapper,SysOrgMapper sysOrgMapper){
//        this.sysOrgMapper = sysOrgMapper;
//        this.sfProcRcmMapper = sfProcRcmMapper;
//    }

    @Override
    public void autoSign(Long orgId, String procNo) {

        // ① 取收货单
        SfProcRcm sfProcRcm = sfProcRcmMapper.selectByOrgAndProcNo(orgId, procNo);
        if (sfProcRcm == null) {
            log.info("收货单不存在，跳过自动签收：{}/{}", orgId, procNo);
            return;
        }
        String vendNo = sfProcRcm.getVendNo();
        if (vendNo == null || vendNo.isEmpty()) {
            log.info("收货单厂商为空，跳过自动签收：{}/{}", orgId, procNo);
            return;
        }

        // ② 厂商 -> 出货组织
        List<SysOrg> sysOrgList = sysOrgMapper.getOrgListByVendNo(vendNo);
        if (sysOrgList.isEmpty()) {
            log.info("厂商 {} 非内部厂商，跳过自动签收", vendNo);     // 常态，静默跳过
            return;
        }
        if (sysOrgList.size() > 1) {                                  // 数据异常，报错
            throw new BusinessException("厂商 " + vendNo + " 对应多个组织，请检查");
        }
        Long salesOrgId = sysOrgList.get(0).getOrgId();
        if (salesOrgId == null) {
            throw new BusinessException("厂商 " + vendNo + " 对应的组织ID为空，请检查");
        }

        // ③ 收货组织 -> 客户ID
        String custId = sysOrgMapper.getCustIdByOrgId(orgId);
        if (custId == null || custId.isEmpty()) {
            throw new BusinessException("组织 " + orgId + " 的 custId 为空，请检查");
        }

        //通过来源组织ID 和 收料单号 +收料组织ID获取所有的关联信息
        List<SignCursorRow> signCursorRowList = sfProcRcmMapper.selectByProcNoAndOrgIdAndSrcOrg(orgId,procNo,salesOrgId);
        //System.out.println(signCursorRowList);
        log.info("追溯到 {} 条明细", signCursorRowList.size());


        // 获取最大的headerId + 1 作为本次的headerId
        Long headerId = psSignMMapper.getMaxHeaderId(salesOrgId) + 1;

        String prefix = sysOrgList.get(0).getMark() + "PSD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 获取签收单号
        Integer maxSeq = psSignMMapper.getMaxSignSeq(salesOrgId, prefix);   // 注意 salesOrgId
        String signNo = prefix + String.format("%04d", (maxSeq == null ? 0 : maxSeq) + 1);


        // 执行插入操作
        PsSignM psSignM = new PsSignM();
        psSignM.setOrgId(salesOrgId);
        psSignM.setHeaderId(headerId);
        psSignM.setSignNo(signNo);
        psSignM.setSignDate(LocalDateTime.now());
        psSignM.setCustId(custId);
        psSignM.setIsSales("Y");
        psSignM.setStatus(3);
        psSignM.setCreUser("258159");
        psSignM.setCreDate(LocalDateTime.now());
        psSignM.setValidUser("257490");
        psSignM.setValidDate(LocalDateTime.now());
        psSignM.setLastUser("257490");
        psSignM.setLastDate(LocalDateTime.now());

        // 插入到ps_sign_m
        int insertMNum = psSignMMapper.insertSignMInfo(psSignM);
        if (insertMNum == 1){
            log.info("插入ps_sign_m成功，header_id为:" + headerId);
        }else {
            throw new BusinessException("签收头插入失败");
        }

//        插入数据到D档 这里直接先将获取到的数据组装好 放入List中
        List<PsSignD> psSignDList = new ArrayList<>();
        Long salesSeq = 0L;
        for(SignCursorRow signCursor:signCursorRowList){
            salesSeq++;
            PsSignD psSignD = new PsSignD();
            psSignD.setHeaderId(headerId);
            psSignD.setOrgId(salesOrgId);
            psSignD.setLineId(signCursor.getLineId());
            psSignD.setSalesId(1L);
            psSignD.setSalesNo(signCursor.getChkNo());
            psSignD.setSalesSeq(salesSeq);
            psSignD.setSeId(signCursor.getPsProcNo());
            BigDecimal psProcSeq = signCursor.getPsProcSeq();
            Long psProcSeqLong;
            if(psProcSeq != null){
                psProcSeqLong = psProcSeq.longValue();
            }else{
                psProcSeqLong = 0L;
            }
            psSignD.setSeSeq(psProcSeqLong);
            psSignD.setProdNo(signCursor.getProdNo());
            psSignD.setRpPt(signCursor.getRpPt());
            psSignD.setPart(signCursor.getPart());
            psSignD.setIsMult(signCursor.getIsMult());
            psSignD.setSignQty(signCursor.getChkQty());
            psSignD.setCreUser("258159");
            psSignD.setLastUser("257490");
            psSignD.setCreDate(LocalDateTime.now());
            psSignD.setLastDate(LocalDateTime.now());
            psSignD.setRcptNo(procNo);
            psSignD.setRcptOrgId(orgId);
            psSignDList.add(psSignD);
        }

        int insertDNum = psSignDMapper.insertDataToPsSignD(psSignDList);
        if(insertDNum == salesSeq){
            log.info("插入明细成功，共插入数据：" + insertDNum + "条");
        }else{
            throw new BusinessException("签收明细插入失败");
        }

        /**
         * 插入数据到ps_sign_s
         * 传入数据直接在组装ps_sign_d的数据时就组装好
         */

    }

    @Override
    public void assertCancelReceiptAllowed(Long orgId, String procNo) {
        // TODO 实现取消收货校验
        throw new UnsupportedOperationException("TODO: assertCancelReceiptAllowed 业务逻辑待实现");
    }

    @Override
    public boolean hasSignRecord(Long orgId, String procNo) {
        // TODO 查询 ps_sign_d 的 rcpt_no / rcpt_org_id 判断是否已签收
        throw new UnsupportedOperationException("TODO: hasSignRecord 业务逻辑待实现");
    }
}
