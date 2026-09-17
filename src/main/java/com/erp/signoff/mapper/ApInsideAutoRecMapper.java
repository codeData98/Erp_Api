package com.erp.signoff.mapper;

import com.erp.signoff.entity.ApInsideAutoRec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内部交易自动签收追踪表 Mapper（中间表）。
 */
@Mapper
public interface ApInsideAutoRecMapper {
    int insertRec(List<ApInsideAutoRec> recList);                                        // 回填自增 tid

    int updateErrorText(@Param("tid") Long tid, @Param("errorText") String errorText);
}
