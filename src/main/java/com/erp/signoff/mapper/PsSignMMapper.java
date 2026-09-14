package com.erp.signoff.mapper;

import com.erp.signoff.entity.PsSignM;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工艺签收确认主表 Mapper（自动签收输出）。
 */
@Mapper
public interface PsSignMMapper {

    // 插入主档信息
    int insertSignMInfo(PsSignM psSignM);
    // 获取最大headerID
    Long  getMaxHeaderId(Long orgId);

    String getSignNoByOrgId(Long orgId,String prefix);
}
