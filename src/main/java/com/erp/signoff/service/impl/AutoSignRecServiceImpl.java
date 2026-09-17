package com.erp.signoff.service.impl;

import com.erp.signoff.entity.ApInsideAutoRec;
import com.erp.signoff.mapper.ApInsideAutoRecMapper;
import com.erp.signoff.service.AutoSignRecService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AutoSignRecServiceImpl implements AutoSignRecService {

    @Autowired
    private ApInsideAutoRecMapper apInsideAutoRecMapper;

    @Override
    public void autoSignRec(List<ApInsideAutoRec> recList) {
        apInsideAutoRecMapper.insertRec(recList);
    }
}
