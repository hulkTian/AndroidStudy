package com.hulk.androidstudy.java_base.reflect;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by tzh on 2020/11/19.
 */
public class InjectUtils {

    public static void injectView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //获得此类的所有成员
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //判断是否被InjectView注解注释
            if (field.isAnnotationPresent(InjectView.class)) {
                //获取注解对象
                InjectView injectView = field.getAnnotation(InjectView.class);
                //获取注解上的值
                if (injectView != null) {
                    int id = injectView.value();
                    View view = activity.findViewById(id);
                    //设置属性的访问权限
                    field.setAccessible(true);
                    try {
                        //反射设值
                        field.set(activity, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else if (field.isAnnotationPresent(AutoWrite.class)) {
                Bundle bundle = activity.getIntent().getExtras();
                if (bundle == null) {
                    continue;
                }
                AutoWrite autoWrite = field.getAnnotation(AutoWrite.class);
                if (autoWrite == null) continue;

                String key = TextUtils.isEmpty(autoWrite.value()) ? field.getName() : autoWrite.value();
                if (bundle.containsKey(key)) {
                    Object value = bundle.get(key);
                    //如果属性是数组，获取属性元素类型，否则返回null
                    Class<?> componentType = field.getType().getComponentType();
                    //属性是数组，判断数组元素是否是可序列化类型
                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objs = (Object[]) value;
                        //创建对应类型的数组并由objs拷贝
                        value = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) field.getType());
                    }
                    field.setAccessible(true);
                    try {
                        field.set(activity, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
