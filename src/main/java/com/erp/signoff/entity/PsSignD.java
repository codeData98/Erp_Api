package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 工艺签收确认明细表（自动签收输出表）。
 * <p>复合主键：(org_id, header_id, line_id)。</p>
 * <p>rcpt_no / rcpt_org_id 回写收货单信息，用于「取消收货」时判断是否已产生签收记录。</p>
 */
@Data
public class PsSignD implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一，出货组织） */
    private Long orgId;

    /** 签收头ID（复合主键之一） */
    private Long headerId;

    /** 行号（复合主键之一） */
    private Long lineId;

    /** 出货ID */
    private Long salesId;

    /** 出货验收单号（= ps_proc_rcm.chk_no） */
    private String salesNo;

    /** 出货序号 */
    private Long salesSeq;

    /** 工艺订单号（= ps_proc_d.proc_no） */
    private String seId;

    /** 工艺订单序号（= ps_proc_d.proc_seq） */
    private Long seSeq;

    /** 产品型号 */
    private String prodNo;

    /** 加工点 */
    private String rpPt;

    /** 部件 */
    private String part;

    /** 是否多件 */
    private Integer isMult;

    /** 签收数量 */
    private BigDecimal signQty;

    /** 创建人 */
    private String creUser;

    /** 创建时间 */
    private LocalDateTime creDate;

    /** 最后修改人 */
    private String lastUser;

    /** 最后修改时间 */
    private LocalDateTime lastDate;

    /** 收货单号（取消收货判断用） */
    private String rcptNo;

    /** 收货组织ID（取消收货判断用） */
    private Long rcptOrgId;
}
