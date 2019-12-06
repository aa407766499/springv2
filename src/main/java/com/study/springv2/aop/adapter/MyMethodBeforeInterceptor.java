package com.study.springv2.aop.adapter;

import com.study.springv2.aop.advice.MyMethodBeforeAdvice;
import com.study.springv2.aop.framework.MyMethodInterceptor;
import com.study.springv2.aop.framework.MyMethodInvocation;

/**
 * @author Huzi114
 * @ClassName: MyMethodBeforeInterceptor
 * @Description:
 * @date 2019/12/6 11:38
 */
public class MyMethodBeforeInterceptor extends MyMethodInterceptor {

    private MyMethodBeforeAdvice methodBeforeAdvice;

    public MyMethodBeforeInterceptor(MyMethodBeforeAdvice methodBeforeAdvice) {
        this.methodBeforeAdvice = methodBeforeAdvice;
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        this.methodBeforeAdvice.before();
        Object retVal = invocation.proceed();
        return retVal;
    }

}
