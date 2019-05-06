package com.k2data.kbc.audit.Utils;

import com.k2data.kbc.audit.dao.AuditNormalLogMapper;
import com.k2data.kbc.audit.dao.AuditExceptionLogMapper;
import com.k2data.kbc.audit.model.AuditNormalLog;
import com.k2data.kbc.audit.model.AuditExceptionLog;

public class LogUtil {


    private static AuditNormalLogMapper auditLogDao = SpringContextHolderUtil.getBean(AuditNormalLogMapper.class);

    private static AuditExceptionLogMapper exLogDao = SpringContextHolderUtil.getBean(AuditExceptionLogMapper.class);

    public static void saveLog(AuditNormalLog log) {
        new SaveLogRunnable(log).run();
    }

    public static void saveExLogRunnable(AuditExceptionLog exlog) {
        new SaveExLogRunnable(exlog).run();
    }

    public static class SaveLogRunnable implements Runnable {

        private AuditNormalLog log;

        public SaveLogRunnable(AuditNormalLog log) {
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
