package com.hulk.androidstudy.java_base.proxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hulk.androidstudy.R;

import java.lang.reflect.Proxy;

/**
 * 代理
 * 静态代理：代理对象需要提前实现被代理对象的接口
 * 动态代理：虚拟机会在类运行时动态的在类中创建代理对象，代理对象通过反射来调用被代理对象。
 * Created by tzh on 2020/11/19.
 */
public class ProxyActivity extends Activity implements View.OnClickListener {
    private TextView tvStaticProxy, tvDynamicProxyJDK, tvDynamicProxyCGLB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        tvStaticProxy = findViewById(R.id.tv_static_proxy);
        tvDynamicProxyJDK = findViewById(R.id.dynamic_proxy_jdk);
        tvDynamicProxyCGLB = findViewById(R.id.dynamic_proxy_cglib);
        tvStaticProxy.setOnClickListener(this);
        tvDynamicProxyJDK.setOnClickListener(this);
        tvDynamicProxyCGLB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_static_proxy:
                testStaticProxy();
                break;
            case R.id.dynamic_proxy_jdk:
                testDynamicProxyJDK();
                break;
            case R.id.dynamic_proxy_cglib:
                testDynamicProxyCGLIB();
                break;
        }
    }

    private void testStaticProxy() {
        IFunction iFunction = new IFunctionImpl();
        IFunctionProxy iFunctionProxy = new IFunctionProxy(iFunction);
        tvStaticProxy.setText(iFunctionProxy.function());
    }

    private void testDynamicProxyJDK() {
//        InvocationHandler invocationHandler = new ProxyHandler(new IFunctionImpl());
//        IFunction iFunction = (IFunction) Proxy.newProxyInstance(IFunction.class.getClassLoader(),
//                new Class[]{IFunction.class}, invocationHandler);
//        tvDynamicProxyJDK.setText(iFunction.function());

        IFunctionImpl iFunction1 = new IFunctionImpl();
        Object o = Proxy.newProxyInstance(iFunction1.getClass().getClassLoader(),
                new Class[]{IFunction.class, IFunction2.class}, (
                        proxy, method, args) ->
                        method.invoke(iFunction1, args
                        ));
        tvDynamicProxyJDK.setText(String.format("%s\n%s", ((IFunction) o).function(),
                ((IFunction2) o).function2()));
    }

    private void testDynamicProxyCGLIB() {

    }
}
