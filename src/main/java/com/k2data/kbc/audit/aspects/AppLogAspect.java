package com.k2data.kbc.audit.aspects;

import com.k2data.kbc.audit.Utils.LogUtils;
import com.k2data.kbc.audit.model.AuditLog;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * before：按order值有小到大的顺序执行
 * after：按order值由大到小的顺序执行
 */
@Component
@Aspect
@Order(4)
public class AppLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(AppLogAspect.class);
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    //单个包@Pointcut("execution(* com.k2data.kbc.audit.controller..*.*(..))")
    //多个包@Pointcut("execution(* *..*method1()) || execution(* *..*method2())")
    //当前包下所有以Controller为后缀的类的任一方法@Pointcut("execution(* com.k2data.kbc..*Controller.*(..))")
    @Pointcut("execution(* com.k2data.kbc..*Controller.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws IOException {
        threadLocal.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("IP: {}", request.getRemoteAddr());
        logger.info("Request URI: {}", request.getRequestURI().toString());
        logger.info("Request URL: {}", request.getRequestURL().toString());
        logger.info("Request Method: {}", request.getMethod());
        logger.info("User-Agent:{}", request.getHeader("User-Agent"));
        logger.info("Class Method:{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("Params:{}", Arrays.toString(joinPoint.getArgs()));
        logger.info("Content-type:{}", request.getHeader("Content-type"));
        AuditLog auditLog = new AuditLog();
        auditLog.setCreateDate(new Date());
        /**
         * todo 集成别的组件需修改使用登录的用户
         */
        String creator = request.getRemoteUser() != null ? request.getRemoteUser() : "creator";
        auditLog.setCreator(creator);
        auditLog.setIp(request.getRemoteAddr());
        auditLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        auditLog.setParams(Arrays.toString(joinPoint.getArgs()));
        auditLog.setRequestMethod(request.getMethod());
        auditLog.setUserAgent(request.getHeader("User-Agent"));
        auditLog.setRequestURL(request.getRequestURL().toString());
        LogUtils.saveLog(auditLog);
    }

    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("doAfter():{}", joinPoint.toString());
    }

    @AfterReturning("pointcut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        logger.info("耗时 :{}", ((System.currentTimeMillis() - threadLocal.get())) + "ms");
    }
}
