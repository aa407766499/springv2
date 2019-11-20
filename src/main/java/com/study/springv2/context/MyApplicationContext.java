package com.study.springv2.context;

import com.study.springv2.annotation.MyAutowired;
import com.study.springv2.beans.MyBeanWrapper;
import com.study.springv2.beans.factory.MyBeanFactory;
import com.study.springv2.beans.factory.config.MyBeanDefinition;
import com.study.springv2.beans.factory.config.MyBeanDefinitionReader;
import com.study.springv2.beans.factory.support.MyBeanDefinitionRegistry;
import com.study.springv2.beans.factory.support.MyDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
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
public class MyApplicationContext extends MyDefaultListableBeanFactory implements MyBeanFactory, MyBeanDefinitionRegistry {

    private String[] configLocations;

    private MyBeanDefinitionReader reader;

    //单例实例缓存
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public MyApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
    }

    @Override
    protected void refresh() {
        //1、定位配置文件
        this.reader = new MyBeanDefinitionReader();

        //2、加载配置文件并解析配置文件
        List<MyBeanDefinition> myBeanDefinitions = this.reader.loadBeanDefinitions(configLocations);

        //3、注册配置信息
        for (MyBeanDefinition myBeanDefinition : myBeanDefinitions) {
            this.registry(myBeanDefinition);
        }

        //4、对于非延时加载的bean进行提前初始化
        doAutowired();

    }

    private void doAutowired() {
        for (MyBeanDefinition myBeanDefinition : this.beanDefinitionMap.values()) {
            if (!myBeanDefinition.isLazyInit()) {
                getBean(myBeanDefinition.getFactoryBeanName());
            }
        }
    }

    @Override
    public Object getBean(String name) {

        MyBeanDefinition mbd = this.beanDefinitionMap.get(name);

        MyBeanWrapper bw;
        if (mbd.isSingleton()) {
            //先从缓存中获取
            if (this.singletonObjects.containsKey(name)) {
                return this.singletonObjects.get(name);
            }
            //缓存中不存在
            //实例化bean
            bw = instantiateBean(name, mbd);

            //依赖注入
            populateBean(name, mbd, bw);

            //放入缓存
            this.singletonObjects.put(name, bw.getWrappedInstance());
        } else {

            bw = instantiateBean(name, mbd);

            //依赖注入
            populateBean(name, mbd, bw);
        }

        if (bw != null) {
            return bw.getWrappedInstance();
        }
        return null;
    }

    private MyBeanWrapper instantiateBean(String name, MyBeanDefinition mbd) {
        if (mbd == null) {
            return null;
        }
        String className = mbd.getBeanClassName();
        try {
            Class<?> beanClass = Class.forName(className);
            Object instance = beanClass.newInstance();
            return new MyBeanWrapper(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void populateBean(String name, MyBeanDefinition mbd, MyBeanWrapper bw) {
        Class<?> beanClass = bw.getWrappedClass();
        for (Field field : beanClass.getFields()) {
            Object value = null;
            if (field.isAnnotationPresent(MyAutowired.class)) {
                MyAutowired ann = field.getAnnotation(MyAutowired.class);
                String beanName = ann.value();
                if (!beanName.equals("")) {
                    value = getBean(beanName);
                } else {
                    value = getBean(field.getType());
                }
            }
            field.setAccessible(true);
            try {
                field.set(bw.getWrappedInstance(),value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object getBean(Class<?> requiredType) {
        return null;
    }

    @Override
    public void registry(MyBeanDefinition myBeanDefinition) {
        super.beanDefinitionMap.put(myBeanDefinition.getFactoryBeanName(), myBeanDefinition);
    }
}
