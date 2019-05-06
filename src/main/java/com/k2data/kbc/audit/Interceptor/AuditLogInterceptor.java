package com.k2data.kbc.audit.Interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.k2data.kbc.audit.Utils.LogUtil;
import com.k2data.kbc.audit.Utils.RequestUtil;
import com.k2data.kbc.audit.model.AuditExceptionLog;
import com.k2data.kbc.audit.model.AuditNormalLog;
import java.lang.reflect.Method;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.LoggerFactory;
public class AuditLogInterceptor implements HandlerInterceptor {

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(AuditLogInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        threadLocal.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception e) throws Exception {
        if (null == e) {
            AuditNormalLog log = new AuditNormalLog();
            HandlerMethod object = (HandlerMethod) handler;
            Method method = object.getMethod();
            log.setClassMethodName(method.getName());
            log.setClassMethodPath(method.getDeclaringClass().getName() + "." + method.getName());
            log.setIp(RequestUtil.getRequestIp(request));
            log.setParams(request.getParameterMap());
            log.setRequestMethod(request.getMethod());
            log.setRequestUrl(request.getRequestURL().toString());
            log.setUserAgent(request.getHeader("User-Agent"));
            log.setRequestStartTime(new Date(threadLocal.get()));
            log.setRequestFinshTime(System.currentTimeMillis() - threadLocal.get());
            log.setReturnTime(new Date());
            LogUtil.saveLog(log);
        } else {
            AuditExceptionLog exLog = new AuditExceptionLog();
            exLog.setExceptionJson(JSON.toJSONString(e, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue));
            exLog.setExceptionCreateTime(new Date(System.currentTimeMillis()));
            exLog.setExceptionMessage(e.getMessage());
            LogUtil.saveExLogRunnable(exLog);
        }
    }

}
