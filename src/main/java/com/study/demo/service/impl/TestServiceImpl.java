package com.study.demo.service.impl;

import com.study.demo.service.TestService;
import com.study.springv2.annotation.MyService;

/**
 * @author Huzi114
 * @ClassName: TestServiceImpl
 * @Description:
 * @date 2019/11/27 11:31
 */
@MyService
public class TestServiceImpl implements TestService {

    @Override
    public String service() {
        System.out.println("调用Service方法");
        return "test";
    }

}
