package com.study.springv2.web.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

    public MyView resolveViewName(String viewName, Locale locale) throws Exception {
        File content = new File(this.getClass().getResource("/" + viewName + defaultFileSuffix).getFile());
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(content)));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        MyView myView = new MyView();
        myView.setContent(sb.toString());
        return myView;
    }

}
