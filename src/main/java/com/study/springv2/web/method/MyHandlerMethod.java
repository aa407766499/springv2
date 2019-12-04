package com.study.springv2.web.method;

import com.study.springv2.annotation.MyRequestParam;
import com.study.springv2.web.servlet.MyModelAndView;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author Huzi114
 * @ClassName: HandlerMethod
 * @Description:
 * @date 2019/11/25 11:01
 */
@Data
public class MyHandlerMethod {

    private Object bean;

    private Class<?> beanClass;

    private Method method;

    private Object[] paramValues;

    public MyHandlerMethod(Object wrappedInstance, Method method) {
        this.bean = wrappedInstance;
        this.beanClass = wrappedInstance.getClass();
        this.method = method;
    }

    public MyModelAndView invokeAndHandle(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Parameter[] param = this.method.getParameters();
        this.paramValues = new Object[param.length];
        for (int i = 0; i < param.length; i++) {
            Parameter methodParameter = param[i];
            if (methodParameter.getType().isAssignableFrom(HttpServletRequest.class)) {
                paramValues[i] = req;
                continue;
            }
            if (methodParameter.getType().isAssignableFrom(HttpServletResponse.class)) {
                paramValues[i] = resp;
                continue;
            }
            if (methodParameter.isAnnotationPresent(MyRequestParam.class)) {
                MyRequestParam requestParam = methodParameter.getAnnotation(MyRequestParam.class);
                String paramName = requestParam.value();
                if (paramName.equals("")) {
                    continue;
                }
                paramValues[i] = parameterMap.get(paramName)[0];
            }
        }
        try {
            return (MyModelAndView) this.method.invoke(this.bean, paramValues);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
