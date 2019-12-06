package com.study.springv2.aop.framework;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Huzi114
 * @ClassName: MyAdvisedSupport
 * @Description:
 * @date 2019/12/4 17:34
 */
@Data
public class MyAdvisedSupport {

    private Object target;

    private Class<?> targetClass;

    private Map<Method, List<Object>> methodCache = new ConcurrentHashMap<>();

    private MyAdvisorChainFactory advisorChainFactory = new MyAdvisorChainFactory();

    public MyAdvisedSupport(Object target) {
        this.target = target;
        this.targetClass = target.getClass();
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception {
        List<Object> cached = this.methodCache.get(method);
        if (cached == null) {
            cached = this.advisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice(this, method, targetClass);
            methodCache.put(method, cached);
        }
        return cached;
    }
}
