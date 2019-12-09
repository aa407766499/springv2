package com.study.springv2.aop.framework;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author Huzi114
 * @ClassName: MyProceedingJoinPoint
 * @Description:
 * @date 2019/12/9 10:20
 */
@Data
public class MyProceedingJoinPoint {

    private Object target;

    private Method method;

    private Object[] args;

    public MyProceedingJoinPoint(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

}
