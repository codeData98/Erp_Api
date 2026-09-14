package com.erp.signoff.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 自动签收主追溯游标行（对应 PL/SQL 中 CURSOR CUR_D 的查询结果）。
 * <p>收货方（sf_proc_rcm / sf_proc_item）+ 出货方（ps_proc_rcm / ps_proc_item / ps_proc_d）字段集合。</p>
 */
@Data
public class SignCursorRow implements Serializable {

    private static final long serialVersionUID = 1L;

    // ====== 收货方数据（sf_proc_rcm / sf_proc_item）======
    /** 收货单号（sf_proc_rcm.proc_no） */
    private String rcptNo;

    /** 内部厂商代码（sf_proc_rcm.vend_no） */
    private String vendNo;

    /** 来源单号（sf_proc_rcm.src_no） */
    private String srcNo;

    /** 委外序号（sf_proc_item.proc_seq），用于关联出货方 */
    private BigDecimal rcptSeq;

    /** 收货验收序号（sf_proc_item.chk_seq） */
    private BigDecimal rcptChkSeq;

    /** 收货数量（sf_proc_item.chk_qty） */
    private BigDecimal chkQty;

    // ====== 出货方数据（ps_proc_rcm / ps_proc_item / ps_proc_d）======
    /** 出货组织（ps_proc_rcm.org_id） */
    private Long orgId;

    /** 出货验收单号（ps_proc_rcm.chk_no） */
    private String chkNo;

    /** 工艺订单号（ps_proc_d.proc_no） */
    private String psProcNo;

    /** 工艺工序序号（ps_proc_d.proc_seq） */
    private BigDecimal psProcSeq;

    /** 产品型号（ps_proc_d.prod_no） */
    private String prodNo;

    /** 加工点（ps_proc_d.rp_pt） */
    private String rpPt;

    /** 部件（ps_proc_d.part） */
    private String part;

    /** 是否多件（ps_proc_d.is_mult） */
    private Integer isMult;
}
