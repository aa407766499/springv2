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

    public MyAfterThrowingInterceptor(MyAfterThrowingAdvice afterThrowingAdvice) {
        this.afterThrowingAdvice = afterThrowingAdvice;
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        try {
            Object retValue = invocation.proceed();
            return retValue;
        } catch (Exception ex) {
            this.afterThrowingAdvice.afterThrowing();
        }
        return null;
    }

}
