package com.study.springv2.web.servlet.mvc.method.annotation;

import com.study.springv2.annotation.MyController;
import com.study.springv2.annotation.MyRequestMapping;
import com.study.springv2.beans.MyBeanWrapper;
import com.study.springv2.context.MyApplicationContext;
import com.study.springv2.web.method.MyHandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * url与Controller中方法的对应关系
 *
 * @author Huzi114
 * @ClassName: MyRequestMappingHandlerMapping
 * @Description:
 * @date 2019/11/22 17:26
 */
public class MyRequestMappingHandlerMapping {

    //url->HandlerMethod
    Map<String, MyHandlerMethod> handlerMethodMap = new HashMap<>();

    public MyRequestMappingHandlerMapping(MyApplicationContext context) {
        init(context);
    }

    private void init(MyApplicationContext context) {
        for (MyBeanWrapper bw : context.factoryBeanInstanceCache.values()) {
            Class<?> wrappedClass = bw.getWrappedClass();
            if (!wrappedClass.isAnnotationPresent(MyController.class)) {
                continue;
            }
            String classUrl = "";
            if (wrappedClass.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping rm = wrappedClass.getAnnotation(MyRequestMapping.class);
                classUrl = rm.value();
            }
            for (Method method : wrappedClass.getMethods()) {
                if (method.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping rm = method.getAnnotation(MyRequestMapping.class);
                    String methodUrl = rm.value();
                    String url = (classUrl + "/" + methodUrl).replaceAll("/+", "/");
                    handlerMethodMap.put(url, new MyHandlerMethod(bw.getWrappedInstance(),method));
                }
            }
        }
    }

    public MyHandlerMethod getHandler(HttpServletRequest req) {
        String url = req.getRequestURI().replace(req.getContextPath(), "").replaceAll("/+", "/");
        return this.handlerMethodMap.get(url);
    }

}
