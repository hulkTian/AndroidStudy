package com.hulk.androidstudy.java_base.reflect;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hulk.androidstudy.R;

/**
 * 反射
 * Created by tzh on 2020/11/19.
 */
public class ReflectActivity extends Activity {

    @InjectView(R.id.tv_reflect)
    private TextView tvCase;

    @AutoWrite
    private String name;

    @AutoWrite("boy")
    private boolean isBoy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        InjectUtils.injectView(this);
        tvCase.setText(String.format("名字：%s \n 是否男孩：%s" , name , isBoy));
    }
}
