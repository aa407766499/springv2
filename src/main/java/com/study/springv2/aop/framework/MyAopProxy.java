package com.study.springv2.aop.framework;

import com.sun.istack.internal.Nullable;

/**
 * @author Huzi114
 * @ClassName: MyAopProxy
 * @Description:
 * @date 2019/12/4 17:28
 */
public interface MyAopProxy {

    Object getProxy();

    Object getProxy(@Nullable ClassLoader classLoader);

}
