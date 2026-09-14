package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 内部交易自动签收追踪表（中间表/输出表）。
 * <p>主键：{@code tid}（序列生成，非自增）。</p>
 * <p>Oracle 中由自治事务写入并立即提交，主事务回滚不影响该记录；
 * 明细行处理出错时把错误信息回写 error_text 后继续处理下一行。</p>
 * <p>Java 实现建议：使用独立事务（REQUIRES_NEW）写入，保证主事务回滚时留痕。</p>
 */
@Data
public class ApInsideAutoRec implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键（序列） */
    private Long tid;

    /** 写入时间 */
    private LocalDateTime insertDate;

    /** 场景描述 */
    private String scene;

    /** 出货组织ID */
    private Integer orgId;

    /** 出货组织类型 */
    private String orgType;

    /** 收货表名 */
    private String rcptTable;

    /** 来源单号（src_no） */
    private String deliverNo;

    /** 收货日期 */
    private LocalDateTime rcptDate;

    /** 收货单号 */
    private String rcptNo;

    /** 收货序号（验收序号 chk_seq） */
    private BigDecimal rcptSeq;

    /** 厂商代码 */
    private String vendNo;

    /** 工艺订单号（proc_no） */
    private String orderNo;

    /** 工艺订单序号（proc_seq） */
    private BigDecimal orderSeq;

    /** 验收日期 */
    private LocalDateTime chkDate;

    /** 验收序号 */
    private BigDecimal chkSeq;

    /** 合格数量 */
    private BigDecimal passQty;

    /** 出货组织ID */
    private Integer salesOrgId;

    /** 出货组织类型 */
    private String salesOrgType;

    /** 出货表名 */
    private String salesTable;

    /** 出货验收单号（chk_no） */
    private String salesNo;

    /** 出货ID */
    private BigDecimal salesId;

    /** 出货序号 */
    private BigDecimal salesSeq;

    /** 工艺订单号（proc_no） */
    private String salesSeid;

    /** 出货数量 */
    private BigDecimal salesQty;

    /** 签收单号 */
    private String signNo;

    /** 签收头ID */
    private BigDecimal headerId;

    /** 行号 */
    private BigDecimal lineId;

    /** 签收数量 */
    private BigDecimal signQty;

    /** 错误信息 */
    private String errorText;
}
