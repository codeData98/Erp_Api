package com.erp.signoff.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工序委外收货主表（鞋厂/收货方，自动签收触发源）。
 * <p>复合主键：(org_id, proc_no)。SQL 写在 Mapper 的注解或 XML 里，按条件查即可。</p>
 */
@Data
public class SfProcRcm implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 组织ID（复合主键之一，收货组织） */
    private Long orgId;

    /** 收货单号（复合主键之一） */
    private String procNo;

    /** 收货日期 */
    private LocalDateTime rcptDate;

    /** 供应厂商代码（内部厂商） */
    private String vendNo;

    /** 送货单号（= 出货方验收单号 CHK_NO） */
    private String truNo;

    /** 来源单号（= 工艺订单主表 SRC_NO） */
    private String srcNo;

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

    /** 收货类型 */
    private String rcptType;

    /** 来源 */
    private String src;

    /** 执行入库类型 */
    private String execInType;

    /** 审核人 */
    private String validUser;

    /** 审核时间 */
    private LocalDateTime validDate;
}
