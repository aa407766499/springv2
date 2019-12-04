package com.study.springv2.web.servlet;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Huzi114
 * @ClassName: MyModelAndeView
 * @Description:
 * @date 2019/11/25 11:22
 */
@Data
@NoArgsConstructor
public class MyModelAndView {

    private String viewName;

    private Map<String, String> model;

    public MyModelAndView(String viewName, Map<String, String> model) {
        this.viewName = viewName;
        this.model = model;
    }

}
