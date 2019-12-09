package com.study.springv2.aop.advice;

import com.study.springv2.aop.framework.MyProceedingJoinPoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Huzi114
 * @ClassName: MyAfterReturningAdvice
 * @Description:
 * @date 2019/12/6 14:24
 */
public class MyAfterReturningAdvice extends MyAbstractAdvice {

    public MyAfterReturningAdvice(Object aspectInstance, Method adviceMethod) {
        super(aspectInstance, adviceMethod);
    }

    public void afterReturning(MyProceedingJoinPoint joinPoint, Object retVal) {
        try {
            this.adviceMethod.invoke(aspectInstance, joinPoint, retVal);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
