package com.study.springv2.beans.factory.config;

/**
 * @author Huzi114
 * @ClassName: MyBeanDefinitionReader
 * @Description:
 * @date 2019/11/18 18:22
 */
public class MyBeanDefinitionReader {

    private String[] configLocation;

    public MyBeanDefinitionReader(String[] configLocation) {
        this.configLocation = configLocation;
    }

}
