package com.erp.signoff.mapper;

import com.erp.signoff.dto.SignCursorRow;
import com.erp.signoff.entity.SfProcRcm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工序委外收货主表 Mapper（自动签收触发源）。
 */
@Mapper
public interface SfProcRcmMapper {


    SfProcRcm selectByOrgAndProcNo(@Param("orgId") Long orgId,@Param("procNo") String procNo);

    List<SignCursorRow> selectByProcNoAndOrgIdAndSrcOrg(@Param("orgId") Long orgId,@Param("procNo") String procNo,@Param("srcOrg") Long srcOrg);
}
