package com.study.springv2.context;

import com.study.springv2.beans.factory.MyBeanFactory;
import com.study.springv2.beans.factory.config.MyBeanDefinitionReader;
import com.study.springv2.beans.factory.support.MyDefaultListableBeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IOC容器实现类
 *
 * @author Huzi114
 * @ClassName: MyApplicationContext
 * @Description:
 * @date 2019/11/18 16:10
 */
public class MyApplicationContext extends MyDefaultListableBeanFactory implements MyBeanFactory {

    private String[] configLocation;

    private MyBeanDefinitionReader reader;

    //单例实例缓存
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public MyApplicationContext(String[] configLocation) {
        this.configLocation = configLocation;
    }

    @Override
    protected void refresh() {
        //1、定位配置文件
        this.reader = new MyBeanDefinitionReader(configLocation);

        //2、加载配置文件并解析配置文件

        //3、注册配置信息

        //4、对于非延时加载的bean进行提前初始化
    }

    @Override
    public Object getBean(String name) {
        return null;
    }

    @Override
    public Object getBean(Class<?> requiredType) {
        return null;
    }

}
