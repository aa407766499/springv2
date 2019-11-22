package com.study.springv2.web.servlet;

import com.study.springv2.context.MyApplicationContext;
import com.study.springv2.web.servlet.mvc.method.annotation.MyRequestMappingHandlerAdapter;
import com.study.springv2.web.servlet.mvc.method.annotation.MyRequestMappingHandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Huzi114
 * @ClassName: MyDispatcherServlet
 * @Description:
 * @date 2019/11/22 17:11
 */
public class MyDispatcherServlet extends HttpServlet {

    protected static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private MyApplicationContext context;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public void init() throws ServletException {
        String contextConfigLocation = this.getServletConfig().getInitParameter(CONTEXT_CONFIG_LOCATION);
        MyApplicationContext context = new MyApplicationContext(contextConfigLocation);
        this.context = context;
        onRefresh(context);
    }

    protected void onRefresh(MyApplicationContext context) {
        initStrategies(context);
    }

    //初始化策略
    protected void initStrategies(MyApplicationContext context) {
        //多文件上传的组件
        initMultipartResolver(context);
        //初始化本地语言环境
        initLocaleResolver(context);
        //初始化主题处理器
        initThemeResolver(context);
        //初始化处理器映射器
        initHandlerMappings(context);
        //初始化处理器适配器
        initHandlerAdapters(context);
        //初始化异常拦截器
        initHandlerExceptionResolvers(context);
        //初始化视图预处理器
        initRequestToViewNameTranslator(context);
        //初始化视图转换器
        initViewResolvers(context);
        //初始化闪存Map管理器（用于重定向请求时，保留请求属性）
        initFlashMapManager(context);
    }

    private void initMultipartResolver(MyApplicationContext context) {

    }

    private void initLocaleResolver(MyApplicationContext context) {

    }

    private void initThemeResolver(MyApplicationContext context) {

    }

    private void initHandlerMappings(MyApplicationContext context) {
        MyRequestMappingHandlerMapping hm = new MyRequestMappingHandlerMapping();
    }

    private void initHandlerAdapters(MyApplicationContext context) {
        MyRequestMappingHandlerAdapter ha = new MyRequestMappingHandlerAdapter();
    }

    private void initHandlerExceptionResolvers(MyApplicationContext context) {

    }

    private void initRequestToViewNameTranslator(MyApplicationContext context) {

    }

    private void initViewResolvers(MyApplicationContext context) {
    }

    private void initFlashMapManager(MyApplicationContext context) {
    }

}
