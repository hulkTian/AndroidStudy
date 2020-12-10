package com.hulk.androidstudy.java_base.proxy;

/**
 * Created by tzh on 2020/11/19.
 */
public class IFunctionImpl implements IFunction,IFunction2 {

    @Override
    public String function() {
        return "实现了IFunction的接口功能";
    }

    @Override
    public String function2() {
        return "实现了IFunction2的接口功能";
    }
}
