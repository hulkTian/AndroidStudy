package com.hulk.androidstudy.java_base.retrofit;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by tzh on 2020/11/19.
 */
public class EnjoyRetrofit {
    final HttpUrl baseUrl;
    final okhttp3.Call.Factory callFactory;
    final Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();

    public EnjoyRetrofit(HttpUrl baseUrl, okhttp3.Call.Factory callFactory) {
        this.callFactory = callFactory;
        this.baseUrl = baseUrl;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service},
                (proxy, method, args) ->
                        //获取需要调用的接口方法
                  loadServiceMethod(method).invoke(args)
        );
    }

    /**
     * 获取api接口中的方法
     */
    private ServiceMethod loadServiceMethod(Method method) {
        //先不上锁，避免synchronized的性能损失
        ServiceMethod result = serviceMethodCache.get(method);
        if (result != null) return result;
        synchronized (serviceMethodCache) {
            result = serviceMethodCache.get(method);
            if (result == null) {
                result = new ServiceMethod.Builder(this, method).build();
                serviceMethodCache.put(method, result);
            }
            return result;
        }
    }

    public static final class Builder {
        private HttpUrl baseUrl;
        private okhttp3.Call.Factory callFactory;

        public Builder callFactory(Call.Factory callFactory) {
            this.callFactory = callFactory;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = HttpUrl.get(baseUrl);
            return this;
        }

        public EnjoyRetrofit build() {
            if (baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }
            if (callFactory == null) {
                callFactory = new OkHttpClient();
            }
            return new EnjoyRetrofit(baseUrl, callFactory);
        }
    }
}
