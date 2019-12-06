package com.study.springv2.aop.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author Huzi114
 * @ClassName: MyJdkDynamicAopProxy
 * @Description:
 * @date 2019/12/4 17:29
 */
public class MyJdkDynamicAopProxy implements MyAopProxy, InvocationHandler {

    private MyAdvisedSupport advised;

    public MyJdkDynamicAopProxy(MyAdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.getClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.advised.getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object target = this.advised.getTarget();
        Class<?> targetClass = target != null ? target.getClass() : this.advised.getTargetClass();
        List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
        MyMethodInvocation myMethodInvocation = new MyMethodInvocation(proxy, target, method, args, targetClass, chain);
        return myMethodInvocation.proceed();
    }

}
