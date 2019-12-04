package com.study.springv2.web.servlet;

import java.io.File;
import java.util.Locale;

/**
 * 视图解析器
 *
 * @author Huzi114
 * @ClassName: MyViewResolver
 * @Description:
 * @date 2019/12/2 18:05
 */
public class MyViewResolver {

    //默认文件后缀
    private String defaultFileSuffix = ".html";

    //静态文件存放的目录
    private String templateRootDir;

    public MyViewResolver(String templateRootDir) {
        this.templateRootDir = templateRootDir;
    }

    public MyView resolveViewName(String viewName, Locale locale) throws Exception {
        String viewFileName = viewName.endsWith(defaultFileSuffix) ? viewName : viewName + defaultFileSuffix;
        File content = new File(this.getClass().getResource("/" + templateRootDir + "/" + viewFileName).getFile());
        return new MyView(content);
    }

}
