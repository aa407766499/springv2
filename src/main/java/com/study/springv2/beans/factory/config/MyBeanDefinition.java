package com.study.springv2.beans.factory.config;

import lombok.Data;

/**
 * 配置信息的容器内部表示形式
 *
 * @author Huzi114
 * @ClassName: MyBeanDefinition
 * @Description:
 * @date 2019/11/18 18:02
 */
@Data
public class MyBeanDefinition {

    private String beanClassName;

    private String factoryBeanName;

    private boolean isSingleton;

    private boolean isLazyInit;

}
