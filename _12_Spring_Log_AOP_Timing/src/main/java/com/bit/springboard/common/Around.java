package com.bit.springboard.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class Around {
    public Object aroungLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("[Log] 로직 처리 전 실행");
        Object returnObj = proceedingJoinPoint.proceed();
        System.out.println("[Log] 로직 처리 후 실행");
        return returnObj;
    }
}
