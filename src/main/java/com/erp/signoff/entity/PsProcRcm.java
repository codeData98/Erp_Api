package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工艺收货（验收）主表（出货方）。
 * <p>复合主键：(org_id, chk_no)。chk_no 与收货方 sf_proc_rcm.tru_no 对应。</p>
 */
@Data
public class PsProcRcm implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一，出货组织） */
    private Long orgId;

    /** 验收单号（复合主键之一） */
    private String chkNo;

    /** 验收日期 */
    private LocalDateTime chkDate;

    /** 验收类型 */
    private String chkType;

    /** 客户ID */
    private String custId;

    /** 状态 */
    private Integer status;

    /** 来源 */
    private String src;

    /** 创建时间 */
    private LocalDateTime creDate;

    /** 最后修改时间 */
    private LocalDateTime lastDate;
}
