package com.erp.signoff.mapper;

import com.erp.signoff.entity.PsSignS;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工艺签收尺码明细表 Mapper（自动签收输出）。
 */
@Mapper
public interface PsSignSMapper {

    int insertBatch(List<PsSignS> psSignSList);
}
