package com.study.springv2.aop.advice;

import java.lang.reflect.InvocationTargetException;
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

    public void afterThrowing() {
        try {
            this.adviceMethod.invoke(aspectInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
