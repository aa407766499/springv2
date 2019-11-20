package com.study.springv2.beans.factory.support;

import com.study.springv2.beans.factory.config.MyBeanDefinition;
import com.study.springv2.context.support.MyAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储配置信息的默认实现类
 *
 * @author Huzi114
 * @ClassName: MyDefaultListableBeanFactory
 * @Description:
 * @date 2019/11/18 17:56
 */
public class MyDefaultListableBeanFactory extends MyAbstractApplicationContext {

    //存储配置信息的map
    protected Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, MyBeanDefinition>();

}
