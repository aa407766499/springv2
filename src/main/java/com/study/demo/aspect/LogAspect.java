package com.study.demo.aspect;

import com.study.springv2.aop.framework.MyProceedingJoinPoint;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Huzi114
 * @ClassName: LogAspect
 * @Description:
 * @date 2019/12/5 16:14
 */
public class LogAspect {

    public void before(MyProceedingJoinPoint joinPoint) {
        try {
            joinPoint.getMethod().invoke(joinPoint.getTarget(), joinPoint.getArgs());
            System.out.println("方法调用前直接调用目标方法");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("方法调用前");
    }

    public void afterReturning(MyProceedingJoinPoint joinPoint, Object retVal) {
        System.out.println("目标方法的返回值：" + retVal);
        System.out.println("方法调用后");
    }

    public void afterThrowing(MyProceedingJoinPoint joinPoint, Exception ex) {
        System.out.println("获取到目标方法抛出的异常" + ex.getMessage());
        System.out.println("方法抛出异常后");
    }

}
