package com.hulk.androidstudy.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.hulk.androidstudy.R
import com.hulk.androidstudy.databinding.ActivityActivitiesHomeBinding

/**
 * 核心主题-Activity
 * Created by tzh on 2020/11/6.
 */
class ActivitiesHomeActivity : FragmentActivity() {
    private lateinit var mBinding : ActivityActivitiesHomeBinding
    private var gameState: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //恢复上次保存的数据
        if (savedInstanceState != null) {
            gameState = savedInstanceState.getString("GAME_STATE_KEY", "")
        }
        //添加布局的方式，除了定义XML文件外，还可实例一个View，并将View插入到ViewGroup中
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_activities_home)
    }

    /**
     * 如果有保存初始数据才会回调该方法。可以再onCreate()中恢复，为避免在onCreate()做太多事情，
     * 可以在这里恢复部分数据。它可能会在onStart()方法后被调用
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mBinding.textView.text = savedInstanceState.getString("TEXT_VIEW_KEY")
    }

    /**
     * activity被临时销毁时，保存初始数据
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("GAME_STATE_KEY", gameState + 1)
        outState.putString("TEXT_VIEW_KEY", mBinding.textView.text as String? + 1)
        super.onSaveInstanceState(outState)
    }

}