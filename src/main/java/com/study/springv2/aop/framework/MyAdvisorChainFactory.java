package com.study.springv2.aop.framework;

import com.study.springv2.aop.adapter.MyAfterReturningInterceptor;
import com.study.springv2.aop.adapter.MyAfterThrowingInterceptor;
import com.study.springv2.aop.adapter.MyMethodBeforeInterceptor;
import com.study.springv2.aop.advice.MyAfterReturningAdvice;
import com.study.springv2.aop.advice.MyAfterThrowingAdvice;
import com.study.springv2.aop.advice.MyMethodBeforeAdvice;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Huzi114
 * @ClassName: MyAdvisorChainFactory
 * @Description:
 * @date 2019/12/5 15:20
 */
public class MyAdvisorChainFactory {

    private static MyAopConfig config = new MyAopConfig();

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(MyAdvisedSupport myAdvisedSupport, Method method, Class<?> targetClass) throws Exception {
        String pointCut = config.getPointCut();
        pointCut = pointCut.replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        String pointCutForClass = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        Pattern pointCutClassPattern = Pattern.compile(pointCutForClass.substring(pointCutForClass.lastIndexOf(" ") + 1));
        Pattern pointCutPattern = Pattern.compile(pointCut);

        Map<String, Method> adviceMethodMap = new HashMap<>();
        Class<?> aspectClass = Class.forName(config.getAspectClass());
        Object aspectInstance = aspectClass.newInstance();
        for (Method adviceMethod : aspectClass.getMethods()) {
            adviceMethodMap.put(adviceMethod.getName(), adviceMethod);
        }

        if (!pointCutClassPattern.matcher(method.getDeclaringClass().getName()).matches()) {
            return null;
        }
        if (!pointCutPattern.matcher(method.toString()).matches()) {
            return null;
        }
        List<Object> advices = new LinkedList<>();
        if (StringUtils.isNoneBlank(config.getAspectBefore())) {
            advices.add(new MyMethodBeforeInterceptor((new MyMethodBeforeAdvice(aspectInstance, adviceMethodMap.get(config.getAspectBefore())))));
        }
        if (StringUtils.isNoneBlank(config.getAspectAfterReturning())) {
            advices.add(new MyAfterReturningInterceptor(((new MyAfterReturningAdvice(aspectInstance, adviceMethodMap.get(config.getAspectAfterReturning()))))));
        }
        if (StringUtils.isNoneBlank(config.getAspectAfterThrow())) {
            advices.add(new MyAfterThrowingInterceptor(((new MyAfterThrowingAdvice(aspectInstance, adviceMethodMap.get(config.getAspectAfterThrow())))), config.getAspectAfterThrowingName()));
        }
        return advices;
    }

}
