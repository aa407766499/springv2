package com.study.springv2.beans.factory.config;

import com.study.springv2.annotation.MyController;
import com.study.springv2.annotation.MyService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Huzi114
 * @ClassName: MyBeanDefinitionReader
 * @Description:
 * @date 2019/11/18 18:22
 */
public class MyBeanDefinitionReader {

    private Properties config = new Properties();

    private String scanPackageKey = "scanPackage";

    private Set<String> classNames = new HashSet<>();

    public MyBeanDefinitionReader(String... configLocations) {
        String configLocation = configLocations[0];
        //加载配置文件
        loadConfigFile(configLocation);
        //解析配置文件
        String scanPackagePath = config.getProperty(scanPackageKey);
        doScan(scanPackagePath);
    }

    public List<MyBeanDefinition> loadBeanDefinitions() throws ClassNotFoundException {
        List<MyBeanDefinition> myBeanDefinitions = new ArrayList<>();
        for (String className : classNames) {
            Class<?> beanClass = Class.forName(className);
            if (beanClass.isInterface()) {
                continue;
            }
            if (!(beanClass.isAnnotationPresent(MyController.class) || beanClass.isAnnotationPresent(MyService.class))) {
                continue;
            }
            myBeanDefinitions.add(new MyBeanDefinition(className, toLowerFirstCase(beanClass.getSimpleName())));
            for (Class<?> classInterface : beanClass.getInterfaces()) {
                myBeanDefinitions.add(new MyBeanDefinition(className, toLowerFirstCase(classInterface.getSimpleName())));
            }
        }
        return myBeanDefinitions;
    }

    private String toLowerFirstCase(String className) {
        char[] charArray = className.toCharArray();
        charArray[0] = (char) (charArray[0] + 32);
        return String.valueOf(charArray);
    }

    private void doScan(String scanPackagePath) {
        String filePath = this.getClass().getResource("/" + scanPackagePath.replaceAll("\\.", "/")).getFile();
        File file = new File(filePath);
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) {
                doScan(scanPackagePath + "." + listFile.getName());
            }
            if (!listFile.getName().endsWith(".class")) {
                continue;
            }
            classNames.add(scanPackagePath + "." + listFile.getName().replaceAll(".class", ""));
        }
    }

    private void loadConfigFile(String configLocation) {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + configLocation.replaceAll("classpath:", ""));
        try {
            config.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
