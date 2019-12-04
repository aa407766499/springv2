package com.study.springv2.beans.factory;

import java.util.List;

/**
 * IOC容器顶层接口
 *
 * @author Huzi114
 * @ClassName: MyBeanFactory
 * @Description:
 * @date 2019/11/18 16:05
 */
public interface MyBeanFactory {

    Object getBean(String name) throws Exception;

    Object getBean(Class<?> requiredType) throws Exception;

    List<String> getBeanDefinitionNames();
}
