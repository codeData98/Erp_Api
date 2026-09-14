package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 组织表（替代 Oracle SY00.SYORG + vw_group_org_vend 视图）。
 * <p>主键：{@code id}（自增）；业务唯一键：(org_id, vend_no)。</p>
 * <p>用途：按 vend_no 查出货组织；按 org_id 取 cust_id；取 mark 拼签收单号前缀。</p>
 */
@Data
public class SysOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 组织ID */
    private Long orgId;

    /** 厂商代码（内部厂商） */
    private String vendNo;

    /** 组织标记（签收单号前缀） */
    private String mark;

    /** 组织类型 */
    private Integer orgType;

    /** 客户ID */
    private String custId;

    /** 状态 */
    private Integer status;

    /** 组织简称 */
    private String shortnmT;

    /** 核算组织ID */
    private Long acOrgid;
}
