package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 工艺验收明细表（出货方）。
 * <p>复合主键：(org_id, proc_no, proc_seq, chk_seq)。</p>
 * <p>持有 chk_no（→ ps_proc_rcm）与 proc_no + proc_seq（→ ps_proc_d）。</p>
 * <p>未签收数量计算的"出货数量"来源：按 org_id + chk_no + proc_no + proc_seq
 * 汇总本表 chk_qty。</p>
 */
@Data
public class PsProcItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一，出货组织） */
    private Long orgId;

    /** 工艺订单号（复合主键之一） */
    private String procNo;

    /** 工艺订单序号（复合主键之一） */
    private BigDecimal procSeq;

    /** 验收序号（复合主键之一） */
    private BigDecimal chkSeq;

    /** 验收类型（5 = 内部出货） */
    private String chkType;

    /** 验收单号（= ps_proc_rcm.chk_no） */
    private String chkNo;

    /** 验收日期 */
    private LocalDateTime chkDate;

    /** 验收数量 */
    private BigDecimal chkQty;

    /** 状态 */
    private Integer status;

    /** 权限部门 */
    private String grtDept;

    /** 权限用户 */
    private String grtUser;

    /** 最后修改人 */
    private String lastUser;

    /** 最后修改时间 */
    private LocalDateTime lastDate;
}
