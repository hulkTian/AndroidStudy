package com.hulk.androidstudy.java_base.proxy;

/**
 * Created by tzh on 2020/11/19.
 */
class IFunctionProxy implements IFunction {
    //被代理对象
    private final IFunction iFunction;

    public IFunctionProxy(IFunction iFunction) {
        this.iFunction = iFunction;
    }

    @Override
    public String function() {
        return "代理对象开始调用被代理对象\n" + iFunction.function() + "\n代理对象结束调用被代理对象";
    }
}
