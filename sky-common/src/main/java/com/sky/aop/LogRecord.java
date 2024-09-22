package com.sky.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogRecord {

    private final Logger logger = LoggerFactory.getLogger(LogRecord.class);

    @Pointcut("execution(* com.sky.controller.admin.*.*(..))")
    public void recordController() {
    }

    @Around("recordController()")
    public Object recordRunTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录controller层执行方法耗时
        long start = System.currentTimeMillis();
        Object data = joinPoint.proceed();
        long end = System.currentTimeMillis();
        Signature signature = joinPoint.getSignature();
        logger.info("{} {}ms", signature.toShortString(), end - start);
        return data;
    }
}
