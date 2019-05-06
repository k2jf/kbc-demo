package com.k2data.kbc.audit.dao;

import com.k2data.kbc.audit.model.AuditExceptionLog;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuditExceptionLogMapper {
    void insert(AuditExceptionLog record);

    List<AuditExceptionLog> selectAll();
}