package com.study.springv2.beans.factory.support;

import com.study.springv2.beans.factory.config.MyBeanDefinition;

/**
 * 配置信息注册表
 *
 * @author Huzi114
 * @ClassName: MyBeanDefinitionRegistry
 * @Description:
 * @date 2019/11/20 15:47
 */
public interface MyBeanDefinitionRegistry {

    void registry(MyBeanDefinition myBeanDefinition);

}
