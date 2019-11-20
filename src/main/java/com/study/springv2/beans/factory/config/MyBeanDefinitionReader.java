package com.study.springv2.beans.factory.config;

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

    public List<MyBeanDefinition> loadBeanDefinitions(String[] configLocations) {
        String configLocation = configLocations[0];
        List<MyBeanDefinition> myBeanDefinitions = new ArrayList<>();
        //加载配置文件
        loadConfigFile(configLocation);
        //解析配置文件
        String scanPackagePath = config.getProperty(scanPackageKey);
        File file = new File(this.getClass().getResource("/" + castFilePath(scanPackagePath)).getPath());
        doScan(file, scanPackagePath);
        for (String className : classNames) {
            MyBeanDefinition myBeanDefinition = new MyBeanDefinition();
            myBeanDefinition.setBeanClassName(className);
            myBeanDefinition.setFactoryBeanName(toLowerFirstCase(className.substring(className.lastIndexOf(".")+1)));
            myBeanDefinition.setLazyInit(false);
            myBeanDefinition.setSingleton(true);
            myBeanDefinitions.add(myBeanDefinition);
        }
        return myBeanDefinitions;
    }

    private String toLowerFirstCase(String className) {
        char[] charArray = className.toCharArray();
        charArray[0] = (char) (charArray[0] + 32);
        return String.valueOf(charArray);
    }

    private void doScan(File file, String scanPackagePath) {
        if (file.isDirectory()) {
            for (File listFile : file.listFiles()) {
                doScan(listFile, scanPackagePath);
            }
        } else {
            String fileName = file.getName();
            if (fileName.endsWith(".class")) {
                String simpleClassName = fileName.substring(0, fileName.lastIndexOf("."));
                classNames.add(scanPackagePath + "." + simpleClassName);
            }
        }
    }

    private void loadConfigFile(String configLocation) {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + castFilePath(configLocation));
        try {
            config.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String castFilePath(String configLocation) {
        return configLocation.replaceAll("\\.", "/").replaceAll("/+", "/");
    }
}
