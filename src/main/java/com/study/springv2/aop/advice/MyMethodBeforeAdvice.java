package com.study.springv2.aop.advice;

import com.study.springv2.aop.framework.MyProceedingJoinPoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Huzi114
 * @ClassName: MyMethodBeforeAdvice
 * @Description:
 * @date 2019/12/6 14:17
 */
public class MyMethodBeforeAdvice extends MyAbstractAdvice {

    public MyMethodBeforeAdvice(Object aspectInstance, Method adviceMethod) {
        super(aspectInstance, adviceMethod);
    }

    public void before(MyProceedingJoinPoint joinPoint) {
        try {
            this.adviceMethod.invoke(aspectInstance, joinPoint);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
