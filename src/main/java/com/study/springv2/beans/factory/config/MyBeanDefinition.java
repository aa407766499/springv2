package com.study.springv2.beans.factory.config;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 配置信息的容器内部表示形式
 *
 * @author Huzi114
 * @ClassName: MyBeanDefinition
 * @Description:
 * @date 2019/11/18 18:02
 */
@Data
@RequiredArgsConstructor
public class MyBeanDefinition {

    @NonNull
    private String beanClassName;

    @NonNull
    private String factoryBeanName;

    private boolean isSingleton = true;

    private boolean isLazyInit = false;

}
