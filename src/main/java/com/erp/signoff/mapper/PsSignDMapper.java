package com.erp.signoff.mapper;

import com.erp.signoff.entity.PsSignD;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工艺签收确认明细表 Mapper（自动签收输出；rcpt_no/rcpt_org_id 用于取消收货判断）。
 */
@Mapper
public interface PsSignDMapper {

    int getLineIdByHeaderId(Long orgId,Long headerId);
    int insertDataToPsSignD(List<PsSignD> psSignDList);
}
