package com.cdyoue.oddJobs.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cdyoue.oddJobs.dto.LoggerRecord;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/9.
 */


@Aspect
@Component
public class LoggerAop {
    private Logger logger = Logger.getLogger(getClass());

    private ThreadLocal<LoggerRecord> threadLocal = new ThreadLocal();

    @Pointcut("execution(public * com.cdyoue.oddJobs.api..*.*(..))")
    private void apiPointcut() {
    }


    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest hsr = attributes.getRequest();
        String url = hsr.getRequestURL().toString();
        String remoteAddr =Optional.ofNullable(hsr.getHeader("IP")).orElse(hsr.getRemoteAddr());
        String method = hsr.getMethod();
        LoggerRecord lr = new LoggerRecord();
        lr.setTarget(url);
        lr.setIp(remoteAddr);
        lr.setMethod(method);
        lr.setClazz(joinPoint.getSignature().getDeclaringTypeName());
        lr.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        threadLocal.remove();
        threadLocal.set(lr);
    }

    @AfterReturning(returning = "result", pointcut = "apiPointcut()")
    public void afterReturnAdvice(Object result) {
        LoggerRecord lr = this.getRecord();
        String des = toJSONString(result);
        lr.setDescription(des);
        lr.setResult("success");
        logger.info(lr.toString());

    }



    @AfterThrowing(throwing = "ex", pointcut = "apiPointcut()")
    public void doRecoveryActions(Throwable ex) {
        LoggerRecord lr = this.getRecord();
        lr.setResult("fail");
        String des = this.toJSONString(ex);
        lr.setDescription(des == null ? null : des.substring(0, 500));
        logger.error(lr.toString());
    }


    public String toJSONString(Object obj) {
        String des = JSONObject.toJSONString(obj,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullNumberAsZero);
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(des);
        des = m.replaceAll("");
        return des;
    }


    public LoggerRecord getRecord() {
        LoggerRecord lr = threadLocal.get() == null? new LoggerRecord():threadLocal.get();
        threadLocal.remove();
        UserMine um = SecurityUtils.getCurrentUserLogin();
        if (um == null) {
            lr.setRole("visitor");
            lr.setUserName("anonymous");
        } else {
            lr.setRole(um.getRole() == 0 ? "person" : um.getRole() == 1 ? "enter" : "admin");
            lr.setUserName(um.getName());
        }
        return lr;
    }
}
