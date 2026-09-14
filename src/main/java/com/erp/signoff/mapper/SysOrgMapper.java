package com.erp.signoff.mapper;

import com.erp.signoff.entity.SysOrg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织表 Mapper（替代 vw_group_org_vend 的组织/厂商查询）。
 */
@Mapper
public interface SysOrgMapper {

//    获取厂商列表
    List<SysOrg> getOrgListByVendNo(@Param("vendNo") String vendNo);
//    获取来源厂商的客户代码
    String getCustIdByOrgId(@Param("orgId") Long orgId);
}
