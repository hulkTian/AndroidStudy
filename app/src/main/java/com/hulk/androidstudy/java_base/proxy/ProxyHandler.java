package com.hulk.androidstudy.java_base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理处理器
 * Created by tzh on 2020/11/19.
 */
class ProxyHandler implements InvocationHandler {

    private final Object realObject;//被代理对象

    public ProxyHandler(Object realObject) {
        this.realObject = realObject;
    }

    /**
     * 调用被代理
     *
     * @param proxy  代理实例
     * @param method 调用的方法
     * @param args   调用方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(realObject, args);
    }
}
