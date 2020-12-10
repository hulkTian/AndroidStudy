package com.hulk.androidstudy.java_base.retrofit;

import com.hulk.androidstudy.java_base.retrofit.annotation.Field;
import com.hulk.androidstudy.java_base.retrofit.annotation.GET;
import com.hulk.androidstudy.java_base.retrofit.annotation.POST;
import com.hulk.androidstudy.java_base.retrofit.annotation.Query;

import okhttp3.Call;

/**
 * Created by tzh on 2020/11/19.
 */
public interface Api {

    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);

    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);
}
