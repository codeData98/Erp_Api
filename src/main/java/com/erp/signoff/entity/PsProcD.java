package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 工艺订单明细表（出货方，产品/加工点/部件）。
 * <p>复合主键：(org_id, proc_no, proc_seq)。</p>
 * <p>自动签收时提供 prod_no、rp_pt、part、is_mult 等签收明细字段。</p>
 */
@Data
public class PsProcD implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一，出货组织） */
    private Long orgId;

    /** 工艺订单号（复合主键之一） */
    private String procNo;

    /** 工艺订单序号（复合主键之一） */
    private BigDecimal procSeq;

    /** 产品型号 */
    private String prodNo;

    /** 工单号 */
    private String wkRno;

    /** 工序序号 */
    private BigDecimal prosSeq;

    /** 订单号码 */
    private String seId;

    /** 订单序号 */
    private BigDecimal seSeq;

    /** 加工点 */
    private String rpPt;

    /** 部件 */
    private String part;

    /** 订单数量 */
    private BigDecimal prQty;

    /** 收货数量 */
    private BigDecimal rcptQty;

    /** 出货数量 */
    private BigDecimal outQty;

    /** 是否分尺码 */
    private String isSize;

    /** 币别 */
    private String curNo;
}
