package com.hulk.androidstudy.java_base.retrofit;

import com.hulk.androidstudy.java_base.retrofit.annotation.Field;
import com.hulk.androidstudy.java_base.retrofit.annotation.GET;
import com.hulk.androidstudy.java_base.retrofit.annotation.POST;
import com.hulk.androidstudy.java_base.retrofit.annotation.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by tzh on 2020/11/19.
 */
public class ServiceMethod {
    private final Call.Factory callFactory;
    private final String relativeUrl;
    private final ParameterHandler[] parameterHandlers;
    private FormBody.Builder formBuild;
    HttpUrl baseUrl;
    String httpMethod;
    HttpUrl.Builder urlBuilder;

    public ServiceMethod(Builder builder) {
        this.callFactory = builder.enjoyRetrofit.callFactory;
        this.baseUrl = builder.enjoyRetrofit.baseUrl;
        this.relativeUrl = builder.relativeUrl;
        this.httpMethod = builder.httpMethod;
        boolean hasBody = builder.hasBody;
        parameterHandlers = builder.parameterHandler;
        if (hasBody) {
            formBuild = new FormBody.Builder();
        }
    }

    /**
     * post请求的请求参数添加
     */
    public void addFiledParameter(String key, String value) {
        //简单处理，全作为表单提交
        formBuild.add(key, value);
    }

    /**
     * get请求的请求参数添加
     */
    public void addQueryParameter(String key, String value) {
        if (urlBuilder == null) {
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        if (urlBuilder != null) {
            urlBuilder.addQueryParameter(key, value);
        }
    }

    /**
     * 构建请求体，执行网络请求
     */
    public Object invoke(Object[] args) {
        //设置请求参数
        for (int i = 0; i < parameterHandlers.length; i++) {
            parameterHandlers[0].apply(this, args[0].toString());
        }
        //获取最终的Url
        HttpUrl url;
        if (urlBuilder == null) {
            urlBuilder = baseUrl.newBuilder(relativeUrl);
        }
        assert urlBuilder != null;
        url = urlBuilder.build();
        //请求体
        FormBody formBody = null;
        //post请求需要请求体
        if (formBuild != null) {
            formBody = formBuild.build();
        }
        Request request = new Request.Builder().url(url).method(httpMethod, formBody).build();
        return callFactory.newCall(request);
    }

    public static class Builder {
        //为了获取url和callFactory
        private final EnjoyRetrofit enjoyRetrofit;
        //方法上的注解
        private final Annotation[] methodAnnotations;
        private final Annotation[][] parameterAnnotations;
        //请求方式
        private String httpMethod;
        //全地址
        private String relativeUrl;
        //是否有请求体（post才会有）
        private boolean hasBody;
        //参数的键数组
        ParameterHandler[] parameterHandler;

        public Builder(EnjoyRetrofit enjoyRetrofit, Method method) {
            this.enjoyRetrofit = enjoyRetrofit;
            //获取方法本身的注解
            methodAnnotations = method.getDeclaredAnnotations();
            //获取方法参数上的注解
            parameterAnnotations = method.getParameterAnnotations();
        }

        public ServiceMethod build() {
            //处理方法上的注解
            for (Annotation methodAnnotation : methodAnnotations) {
                if (methodAnnotation instanceof POST) {
                    httpMethod = "POST";
                    relativeUrl = ((POST) methodAnnotation).value();
                    hasBody = true;
                } else if (methodAnnotation instanceof GET) {
                    httpMethod = "GET";
                    relativeUrl = ((GET) methodAnnotation).value();
                    hasBody = false;
                }
            }
            //因为每个参数的注解只有一个所以可以直接用二维数组的长度来定义数组长度
            int length = parameterAnnotations.length;
            parameterHandler = new ParameterHandler[length];
            //处理参数上的注解
            for (int i = 0; i < length; i++) {
                //单个参数上的所有注解
                Annotation[] annotations = parameterAnnotations[i];
                for (Annotation parameterAnnotation : annotations) {
                    if (parameterAnnotation instanceof Field) {
                        parameterHandler[i] = new ParameterHandler.FiledParameterHandler(
                                ((Field) parameterAnnotation).value());
                    } else if (parameterAnnotation instanceof Query) {
                        parameterHandler[i] = new ParameterHandler.QueryParameterHandler(
                                ((Query) parameterAnnotation).value());
                    }
                }
            }
            return new ServiceMethod(this);
        }
    }
}
