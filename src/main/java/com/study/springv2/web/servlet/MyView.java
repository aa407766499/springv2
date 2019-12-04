package com.study.springv2.web.servlet;

import com.sun.istack.internal.Nullable;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author Huzi114
 * @ClassName: MyView
 * @Description:
 * @date 2019/12/2 18:06
 */
@Data
@RequiredArgsConstructor
public class MyView {

    @NonNull
    private File content;

    private String placeHolderPrefix = "#\\{";

    private String placeHolderSuffix = "}";

    public void render(@Nullable Map<String, String> model,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(content)));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        String fileContent = sb.toString();
        for (String key : model.keySet()) {
            String keyPlaceholder = placeHolderPrefix + key + placeHolderSuffix;
            fileContent = fileContent.replaceAll(keyPlaceholder, model.get(key));
        }
        response.getWriter().write(fileContent);
    }

}
