package com.study.demo.controller;

import com.study.demo.service.TestService;
import com.study.springv2.annotation.MyAutowired;
import com.study.springv2.annotation.MyController;
import com.study.springv2.annotation.MyRequestMapping;
import com.study.springv2.annotation.MyRequestParam;
import com.study.springv2.web.servlet.MyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Huzi114
 * @ClassName: TestController
 * @Description:
 * @date 2019/11/27 11:30
 */
@MyController
@MyRequestMapping("/demo")
public class TestController {

    @MyAutowired
    private TestService testService;

    @MyRequestMapping("/test1")
    public void test1(HttpServletRequest request, HttpServletResponse response, @MyRequestParam("name") String name, @MyRequestParam("age") String age) {
        String service = testService.service();
        System.out.println(service);
        System.out.println("age is " + age + ",name is " + name);
    }

    @MyRequestMapping("/test2")
    public void test2(HttpServletRequest request, HttpServletResponse response, @MyRequestParam("a") String a, @MyRequestParam("b") String b) {
        String c = a + b;
        try {
            response.getWriter().write(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/test3")
    public MyModelAndView test3(HttpServletRequest request, HttpServletResponse response, @MyRequestParam("name") String name, @MyRequestParam("age") String age) {
        Map<String, String> model = new HashMap<>();
        model.put("name", name);
        model.put("age", age);
        MyModelAndView myModelAndView = new MyModelAndView();
        myModelAndView.setViewName("helloWorld");
        myModelAndView.setModel(model);
        return myModelAndView;
    }
}
