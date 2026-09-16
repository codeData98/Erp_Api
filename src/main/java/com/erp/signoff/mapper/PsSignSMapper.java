package com.erp.signoff.mapper;

import com.erp.signoff.entity.PsSignS;
import com.erp.signoff.entity.SfProcRcsize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 工艺签收尺码明细表 Mapper（自动签收输出）。
 */
@Mapper
public interface PsSignSMapper {

    int insertBatch(List<PsSignS> psSignSList);

    List<SfProcRcsize> getPsSignSList(@Param("orgId") Long orgId, @Param("procNo") String procNo, @Param("procSeq") BigDecimal psProcSeq, @Param("chkNo") String chkNo);
}
