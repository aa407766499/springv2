package com.study.springv2.aop.framework;

import lombok.Data;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Huzi114
 * @ClassName: MyAopConfig
 * @Description:
 * @date 2019/12/5 15:29
 */
@Data
public class MyAopConfig {

    private String pointCut;
    private String aspectBefore;
    private String aspectAfterReturning;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;

    public MyAopConfig() {
        try {
            Properties config = new Properties();
            config.load(this.getClass().getResourceAsStream("/" + "application.properties"));
            this.init(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(Properties config) {
        this.pointCut = config.getProperty("pointCut");
        this.aspectClass = config.getProperty("aspectClass");
        this.aspectBefore = config.getProperty("aspectBefore");
        this.aspectAfterReturning = config.getProperty("aspectAfterReturning");
        this.aspectAfterThrow = config.getProperty("aspectAfterThrow");
        this.aspectAfterThrowingName = config.getProperty("aspectAfterThrowingName");
    }

}
