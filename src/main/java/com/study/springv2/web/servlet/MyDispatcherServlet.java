package com.study.springv2.web.servlet;

import com.study.springv2.context.MyApplicationContext;
import com.study.springv2.web.method.MyHandlerMethod;
import com.study.springv2.web.servlet.mvc.method.annotation.MyRequestMappingHandlerAdapter;
import com.study.springv2.web.servlet.mvc.method.annotation.MyRequestMappingHandlerMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Huzi114
 * @ClassName: MyDispatcherServlet
 * @Description:
 * @date 2019/11/22 17:11
 */
public class MyDispatcherServlet extends HttpServlet {

    protected static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private MyApplicationContext context;

    private MyRequestMappingHandlerMapping hm;

    private MyRequestMappingHandlerAdapter ha;

    private MyViewResolver vr;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        MyHandlerMethod mappedHandler = getHandler(req);
        if (mappedHandler == null) {
            return;
        }
        MyRequestMappingHandlerAdapter ha = getHandlerAdapter(mappedHandler);
        if (ha == null) {
            return;
        }
        MyModelAndView mv = ha.handler(req, resp, mappedHandler);
        MyView myView = vr.resolveViewName(mv.getViewName(), null);
        myView.render(mv.getModel(), req, resp);
    }

    private MyHandlerMethod getHandler(HttpServletRequest req) {
        return hm.getHandler(req);
    }

    private MyRequestMappingHandlerAdapter getHandlerAdapter(MyHandlerMethod mappedHandler) {
        if (ha.supports(mappedHandler)) {
            return ha;
        }
        return null;
    }

    @Override
    public void init() {
        String contextConfigLocation = this.getServletConfig().getInitParameter(CONTEXT_CONFIG_LOCATION);
        MyApplicationContext context = null;
        context = new MyApplicationContext(contextConfigLocation);
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
        hm = new MyRequestMappingHandlerMapping(context);
    }

    private void initHandlerAdapters(MyApplicationContext context) {
        ha = new MyRequestMappingHandlerAdapter();
    }

    private void initHandlerExceptionResolvers(MyApplicationContext context) {
        vr = new MyViewResolver();
    }

    private void initRequestToViewNameTranslator(MyApplicationContext context) {

    }

    private void initViewResolvers(MyApplicationContext context) {

    }

    private void initFlashMapManager(MyApplicationContext context) {
    }

}
