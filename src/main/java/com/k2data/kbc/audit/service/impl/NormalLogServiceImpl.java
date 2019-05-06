package com.k2data.kbc.audit.service.impl;

import com.k2data.kbc.audit.dao.AuditNormalLogMapper;
import com.k2data.kbc.audit.model.AuditNormalLog;
import com.k2data.kbc.audit.service.NormalLogService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class NormalLogServiceImpl implements NormalLogService {

    @Autowired
    AuditNormalLogMapper mapper;

    @Override
    public List<AuditNormalLog> list(Map<String, Object> map) {
        return mapper.list(map);
    }
}
