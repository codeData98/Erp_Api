package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工艺订单主表（出货方）。
 * <p>复合主键：(org_id, proc_no)。</p>
 * <p>通过 src_no 与收货方 sf_proc_rcm.src_no 关联，得到工艺订单号与出货组织。</p>
 */
@Data
public class PsProcM implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一，出货组织） */
    private Long orgId;

    /** 工艺订单号（复合主键之一） */
    private String procNo;

    /** 客户ID */
    private String custId;

    /** 下单日期 */
    private LocalDateTime prDate;

    /** 下单部门 */
    private String prDept;

    /** 来源单号（= 收货方 src_no） */
    private String srcNo;

    /** 来源组织ID */
    private Long srcOrgid;

    /** 订单类型 */
    private String prType;

    /** 来源 */
    private String src;

    /** 状态 */
    private Integer status;

    /** 打印状态 */
    private String prtStatus;

    /** 权限部门 */
    private String grtDept;

    /** 权限用户 */
    private String grtUser;

    /** 最后修改人 */
    private String lastUser;

    /** 最后修改时间 */
    private LocalDateTime lastDate;
}
