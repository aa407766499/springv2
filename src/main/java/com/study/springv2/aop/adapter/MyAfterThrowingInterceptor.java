package com.study.springv2.aop.adapter;

import com.study.springv2.aop.advice.MyAfterThrowingAdvice;
import com.study.springv2.aop.framework.MyMethodInterceptor;
import com.study.springv2.aop.framework.MyMethodInvocation;

/**
 * @author Huzi114
 * @ClassName: MyAfterThrowingInterceptor
 * @Description:
 * @date 2019/12/6 14:30
 */
public class MyAfterThrowingInterceptor extends MyMethodInterceptor {

    private MyAfterThrowingAdvice afterThrowingAdvice;

    private Class<?> exception;

    public MyAfterThrowingInterceptor(MyAfterThrowingAdvice afterThrowingAdvice, String aspectAfterThrowingName) {
        this.afterThrowingAdvice = afterThrowingAdvice;
        try {
            this.exception = Class.forName(aspectAfterThrowingName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Exception ex) {
            if (exception.isAssignableFrom(ex.getClass())) {
                this.afterThrowingAdvice.afterThrowing(invocation.getJoinPoint(), ex);
            }
            throw ex;
        }
    }

}
