package com.study.demo.aspect;

/**
 * @author Huzi114
 * @ClassName: LogAspect
 * @Description:
 * @date 2019/12/5 16:14
 */
public class LogAspect {


    public void before() {
        System.out.println("方法调用前");
    }

    public void afterReturning() {
        System.out.println("方法调用后");
    }

    public void afterThrowing() {
        System.out.println("方法抛出异常后");
    }

}
