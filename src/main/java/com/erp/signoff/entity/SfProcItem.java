package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 工序委外收货明细表（收货方）。
 * <p>复合主键：(org_id, proc_no, proc_seq, chk_seq)。</p>
 * <p>proc_seq 为委外序号，与出货方 PS_PROC_ITEM.PROC_SEQ、PS_PROC_D.PROC_SEQ 严格对应。</p>
 */
@Data
public class SfProcItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一） */
    private Long orgId;

    /** 收货单号（复合主键之一） */
    private String procNo;

    /** 委外序号（复合主键之一，收货明细行号） */
    private BigDecimal procSeq;

    /** 验收序号（复合主键之一） */
    private BigDecimal chkSeq;

    /** 验收日期 */
    private LocalDateTime chkDate;

    /** 收货数量 */
    private BigDecimal chkQty;

    /** 合格数量 */
    private BigDecimal passQty;

    /** 状态 */
    private Integer status;

    /** 权限用户 */
    private String grtUser;

    /** 权限部门 */
    private String grtDept;

    /** 最后修改人 */
    private String lastUser;

    /** 最后修改时间 */
    private LocalDateTime lastDate;

    /** 工单号 */
    private String wkId;

    /** 加工点 */
    private String rpPt;

    /** 订单项号 */
    private BigDecimal seSeq;

    /** 部件 */
    private String partNo;

    /** 是否质检 */
    private String isQc;

    /** 是否质检合格 */
    private String isQcRpass;
}
