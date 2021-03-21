package com.hulk.androidstudy.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.hulk.androidstudy.R

/**
 * Activity的生命周期案例
 * Created by tzh on 2020/11/9.
 */
class ActivityLifeCycleCaseActivity:FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_case)
        Log.d("LifeCycleCaseActivity", "ActivityLifeCycleCaseActivity onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LifeCycleCaseActivity", "ActivityLifeCycleCaseActivity onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycleCaseActivity", "ActivityLifeCycleCaseActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycleCaseActivity", "ActivityLifeCycleCaseActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycleCaseActivity", "ActivityLifeCycleCaseActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycleCaseActivity", "ActivityLifeCycleCaseActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycleCaseActivity", "ActivityLifeCycleCaseActivity onDestroy")
    }
}