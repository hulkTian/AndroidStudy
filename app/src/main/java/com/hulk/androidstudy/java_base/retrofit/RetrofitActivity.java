package com.hulk.androidstudy.java_base.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.hulk.androidstudy.R;

import java.io.IOException;

/**
 * 利用注解、反射、动态代理实现Retrofit
 * 1.定义注解，设计注解的使用方式
 * 2.定义网络请求接口，并使用注解定义接口方法
 * 3.使用动态代理获取请求接口的代理对象，在代理对象的回调方法中处理被调用的请求方法
 * 4.通过反射拿到请求方法的所有注解
 * 5.根据注解构建请求方式、请求体、请求对象
 * 6.业务逻辑调用接口的请求方法，传递具体的参数，并实现请求返回接口的处理逻辑。
 * Created by tzh on 2020/11/19.
 */
public class RetrofitActivity extends Activity {
    private static final String TAG = "RetrofitActivity";
    private Api enjoyWeatherApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        EnjoyRetrofit enjoyRetrofit = new EnjoyRetrofit.Builder().baseUrl("https://restapi.amap.com").build();
        enjoyWeatherApi = enjoyRetrofit.create(Api.class);
        findViewById(R.id.tv_get).setOnClickListener(this::enjoyGet);
        findViewById(R.id.tv_post).setOnClickListener(this::enjoyPost);
    }

    public void enjoyGet(View view) {
        okhttp3.Call call = enjoyWeatherApi.getWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b");
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.i(TAG, "onResponse enjoy get: " + response.body().string());
                response.close();
            }
        });
    }

    public void enjoyPost(View view) {
        okhttp3.Call call = enjoyWeatherApi.postWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b");
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.i(TAG, "onResponse enjoy post: " + response.body().string());
                response.close();
            }
        });
    }
}
