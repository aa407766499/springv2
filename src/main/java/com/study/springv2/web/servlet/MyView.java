package com.study.springv2.web.servlet;

import com.sun.istack.internal.Nullable;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Huzi114
 * @ClassName: MyView
 * @Description:
 * @date 2019/12/2 18:06
 */
@Data
public class MyView {

    private String content;

    private String placeHolderPrefix = "#\\{";

    private String placeHolderSuffix = "}";

    public void render(@Nullable Map<String, String> model,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        for (String key : model.keySet()) {
            String keyPlaceholder = placeHolderPrefix + key + placeHolderSuffix;
            content = content.replaceAll(keyPlaceholder, model.get(key));
        }
        response.getWriter().write(content);
    }

}
