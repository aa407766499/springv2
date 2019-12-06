package com.study.springv2.aop.adapter;

import com.study.springv2.aop.advice.MyAfterReturningAdvice;
import com.study.springv2.aop.framework.MyMethodInterceptor;
import com.study.springv2.aop.framework.MyMethodInvocation;

/**
 * @author Huzi114
 * @ClassName: MyAfterReturningInterceptor
 * @Description:
 * @date 2019/12/6 14:27
 */
public class MyAfterReturningInterceptor extends MyMethodInterceptor {

    private MyAfterReturningAdvice afterReturningAdvice;

    public MyAfterReturningInterceptor(MyAfterReturningAdvice afterReturningAdvice) {
        this.afterReturningAdvice = afterReturningAdvice;
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        Object retVal = invocation.proceed();
        this.afterReturningAdvice.afterReturning(retVal);
        return retVal;
    }

}
