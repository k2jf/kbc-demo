package com.k2data.kbc.audit.dao;

import com.k2data.kbc.audit.model.AuditNormalLog;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuditNormalLogMapper {

    void insert(AuditNormalLog object);

    List<AuditNormalLog> list(Map<String, Object> map);
}