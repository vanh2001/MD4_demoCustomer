package com.codegym.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyLogger {

    @AfterReturning(pointcut = "within(com.codegym.controller.*)", returning = "result")
    public void logging(JoinPoint joinPoint, Object result){
        System.out.println("Start log");
        String name = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        //Ghi ra tên method
        System.out.println(name +"."+ method);
        if (result == null){
            System.out.println("Null");
        }else {
            System.out.println(result.hashCode());
        }
    }
}
