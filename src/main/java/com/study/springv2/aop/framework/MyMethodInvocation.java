package com.study.springv2.aop.framework;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Huzi114
 * @ClassName: MyMethodInvocation
 * @Description:
 * @date 2019/12/5 15:16
 */
public class MyMethodInvocation extends MyInvocation {

    private Object proxy;

    private Object target;

    private Method method;

    private Object[] args;

    private Class<?> targetClass;

    private List<Object> chain;

    private int currInterceptorIndex = -1;

    public MyMethodInvocation(Object proxy, Object target, Method method, Object[] args, Class<?> targetClass, List<Object> chain) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.args = args;
        this.targetClass = targetClass;
        this.chain = chain;
    }

    @Override
    public Object[] getArguments() {
        return this.args;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public Object proceed() {
        try {
            currInterceptorIndex++;
            if (currInterceptorIndex >= chain.size()) {
                return method.invoke(target, args);
            }
            Object interceptor = chain.get(currInterceptorIndex);
            if (interceptor instanceof MyMethodInterceptor) {
                return ((MyMethodInterceptor) interceptor).invoke(this);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public MyProceedingJoinPoint getJoinPoint() {
        return new MyProceedingJoinPoint(this.target, this.method, this.args);
    }

}
