package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工艺签收确认主表（自动签收输出表）。
 * <p>复合主键：(org_id, header_id)。</p>
 * <p>自动签收直接置 status = 3（已审核），is_sales = 'Y'。</p>
 * <p>sign_no 规则：sys_org.mark + 'PSD' + YYMMDD + 4 位当日流水。</p>
 */
@Data
public class PsSignM implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一，出货组织） */
    private Long orgId;

    /** 签收头ID（复合主键之一，序列生成） */
    private Long headerId;

    /** 签收单号 */
    private String signNo;

    /** 签收日期 */
    private LocalDateTime signDate;

    /** 客户ID */
    private String custId;

    /** 是否出货单签收（Y） */
    private String isSales;

    /** 状态（3 = 已审核） */
    private Integer status;

    /** 创建人 */
    private String creUser;

    /** 创建时间 */
    private LocalDateTime creDate;

    /** 审核人 */
    private String validUser;

    /** 审核时间 */
    private LocalDateTime validDate;

    /** 最后修改人 */
    private String lastUser;

    /** 最后修改时间 */
    private LocalDateTime lastDate;

    /** 来源类型（1:手工录入/2:数据初始化） */
    private String srcType;
}
