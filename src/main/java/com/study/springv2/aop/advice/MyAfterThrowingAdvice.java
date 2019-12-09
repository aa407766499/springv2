package com.study.springv2.aop.advice;

import com.study.springv2.aop.framework.MyProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author Huzi114
 * @ClassName: MyAfterThrowingAdvice
 * @Description:
 * @date 2019/12/6 14:25
 */
public class MyAfterThrowingAdvice extends MyAbstractAdvice {

    public MyAfterThrowingAdvice(Object aspectInstance, Method adviceMethod) {
        super(aspectInstance, adviceMethod);
    }

    public void afterThrowing(MyProceedingJoinPoint joinPoint, Exception ex) {
        try {
            this.adviceMethod.invoke(aspectInstance, joinPoint, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
