package com.k2data.kbc.audit.service;

import com.k2data.kbc.audit.model.AuditNormalLog;
import java.util.List;
import java.util.Map;

public interface NormalLogService {

    List<AuditNormalLog> list(Map<String, Object> map);
}
