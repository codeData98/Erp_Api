package com.erp.signoff.mapper;

import com.erp.signoff.entity.PsSignM;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工艺签收确认主表 Mapper（自动签收输出）。
 */
@Mapper
public interface PsSignMMapper {

    int insertSignMInfo(PsSignM psSignM);

}
