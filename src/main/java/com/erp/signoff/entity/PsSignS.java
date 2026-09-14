package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 工艺签收尺码明细表（自动签收输出表）。
 * <p>复合主键：(org_id, header_id, line_id, size_no)。</p>
 * <p>尺码数据来源：收货方 sf_proc_rcsize.chk_qty；无尺码数据时插入一条 size_no = '*' 的占位记录。</p>
 */
@Data
public class PsSignS implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一，出货组织） */
    private Long orgId;

    /** 签收头ID（复合主键之一） */
    private Long headerId;

    /** 行号（复合主键之一，与 ps_sign_d.line_id 对应） */
    private Long lineId;

    /** 尺码号（复合主键之一） */
    private String sizeNo;

    /** 尺码序号 */
    private BigDecimal sizeSeq;

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
}
