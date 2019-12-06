package com.study.springv2.aop.framework;

/**
 * @author Huzi114
 * @ClassName: MyCglibAopProxy
 * @Description:
 * @date 2019/12/4 17:30
 */
public class MyCglibAopProxy implements MyAopProxy {

    private MyAdvisedSupport advised;

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }

}
