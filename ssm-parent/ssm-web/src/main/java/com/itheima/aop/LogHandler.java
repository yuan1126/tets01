package com.itheima.aop;

import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 切面日志处理
 */
@Aspect
@Component
public class LogHandler {

    @Autowired
    private SysLogService logService;

    @Autowired
    private HttpServletRequest request;

    @Before("execution(* com.itheima.controller.*Controller.*(..))")
    public void doBefore(JoinPoint joinPoint){
        addLog(joinPoint);
    }

    /*
    @Around("execution(* com.itheima.controller.*Controller.*(..))")
    public Object around(ProceedingJoinPoint pjp){
        调用方法前
        Object obj = pjp.proceed();
        调用方法后
        return obj;
    }
    */

    @After("execution(* com.itheima.controller.*Controller.*(..))")
    public void doAfter(JoinPoint joinPoint){
        addLog(joinPoint);
    }

    private void addLog(JoinPoint joinPoint){
        // 切入点
        Signature signature = joinPoint.getSignature();
        // controller类
        String clsName = signature.getDeclaringType().getName();
        // 方法名
        String methodName = clsName+ "." + signature.getName();
        // 登陆用户的名称
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        String ip = request.getRemoteAddr();

        SysLog log = new SysLog();
        log.setIp(ip);
        log.setUsername(username);
        log.setVisitTime(new Date());
        log.setMethod(methodName);

        logService.add(log);
    }
}
