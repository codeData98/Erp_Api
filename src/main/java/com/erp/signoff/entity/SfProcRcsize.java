package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 工序委外收货尺码明细表（收货方尺码，自动签收时签收尺码的来源）。
 * <p>复合主键：(org_id, proc_no, proc_seq, chk_seq, size_no)。</p>
 * <p>业务规则：收多少签多少，签收尺码数量直接取本表 chk_qty。</p>
 */
@Data
public class SfProcRcsize implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一） */
    private Long orgId;

    /** 收货单号（复合主键之一） */
    private String procNo;

    /** 委外序号（复合主键之一） */
    private BigDecimal procSeq;

    /** 验收序号（复合主键之一） */
    private BigDecimal chkSeq;

    /** 尺码号（复合主键之一） */
    private String sizeNo;

    /** 尺码序号 */
    private BigDecimal sizeSeq;

    /** 收货尺码数量 */
    private BigDecimal chkQty;

    /** 合格尺码数量 */
    private BigDecimal passQty;
}
