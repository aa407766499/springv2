package com.study.springv2.beans.factory.config;

/**
 * @author Huzi114
 * @ClassName: MyBeanPostProcessor
 * @Description:
 * @date 2019/12/4 14:18
 */
public class MyBeanPostProcessor {

    //初始化前处理回调
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    //初始化后处理回调
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

}
