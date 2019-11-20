package com.study.springv2.beans;

import lombok.Getter;

/**
 * 实例包装器
 *
 * @author Huzi114
 * @ClassName: MyBeanWrapper
 * @Description:
 * @date 2019/11/20 17:11
 */
@Getter
public class MyBeanWrapper {

    private Object wrappedInstance;

    private Class<?> wrappedClass;

    public MyBeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
        this.wrappedClass = wrappedInstance.getClass();
    }

}
