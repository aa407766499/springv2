package com.study.springv2.aop.advice;

import java.lang.reflect.Method;

/**
 * @author Huzi114
 * @ClassName: MyAbstractAdvice
 * @Description:
 * @date 2019/12/6 14:17
 */
public class MyAbstractAdvice {

    protected Object aspectInstance;

    protected Method adviceMethod;

    public MyAbstractAdvice(Object aspectInstance, Method adviceMethod) {
        this.aspectInstance = aspectInstance;
        this.adviceMethod = adviceMethod;
    }

}
