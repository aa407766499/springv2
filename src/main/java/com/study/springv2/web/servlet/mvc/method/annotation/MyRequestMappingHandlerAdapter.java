package com.study.springv2.web.servlet.mvc.method.annotation;

import com.study.springv2.web.method.MyHandlerMethod;
import com.study.springv2.web.servlet.MyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Huzi114
 * @ClassName: MyRequestMappingHandlerAdapter
 * @Description:
 * @date 2019/11/22 17:27
 */
public class MyRequestMappingHandlerAdapter {

    public boolean supports(Object mappedHandler) {
        return mappedHandler instanceof MyHandlerMethod;
    }

    public MyModelAndView handler(HttpServletRequest req, HttpServletResponse resp, MyHandlerMethod mappedHandler) {
        return invokeHandlerMethod(req, resp, mappedHandler);
    }

    private MyModelAndView invokeHandlerMethod(HttpServletRequest req, HttpServletResponse resp, MyHandlerMethod mappedHandler) {
        return mappedHandler.invokeAndHandle(req, resp);
    }
}
