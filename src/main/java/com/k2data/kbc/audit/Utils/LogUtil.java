package com.k2data.kbc.audit.Utils;

import com.k2data.kbc.audit.dao.AuditLogMapper;
import com.k2data.kbc.audit.dao.AuditExceptionLogMapper;
import com.k2data.kbc.audit.model.AuditLog;
import com.k2data.kbc.audit.model.AuditExceptionLog;

public class LogUtil {


    private static AuditLogMapper auditLogDao = SpringContextHolderUtil.getBean(AuditLogMapper.class);

    private static AuditExceptionLogMapper exLogDao = SpringContextHolderUtil.getBean(AuditExceptionLogMapper.class);

    public static void saveLog(AuditLog log) {
        new SaveLogRunnable(log).run();
    }

    public static void saveExLogRunnable(AuditExceptionLog exlog) {
        new SaveExLogRunnable(exlog).run();
    }

    public static class SaveLogRunnable implements Runnable {

        private AuditLog log;

        public SaveLogRunnable(AuditLog log) {
            log.setId(null);
            this.log = log;
        }

        @Override
        public void run() {
            auditLogDao.insert(log);
        }
    }

    public static class SaveExLogRunnable implements Runnable {

        private AuditExceptionLog exLog;

        public SaveExLogRunnable(AuditExceptionLog exLog) {
            this.exLog = exLog;
        }

        @Override
        public void run() {
            exLogDao.insert(exLog);
        }
    }
}
