package com.hulk.androidstudy.java_base.rxjava

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.hulk.androidstudy.R
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.activity_rx_java.*

/**
 * Created by tzh on 2020/12/4.
 */
class RXJavaActivity : Activity(), View.OnClickListener {
    private val useRxJava = UseRxJava()
    private val TAG = "RXJavaActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        bt_test_1.setOnClickListener(this)
//        RxView.clicks(bt_test_1)
//            .throttleFirst(2000, TimeUnit.MILLISECONDS)
//            .subscribe(object : Observer<Any> {
//                override fun onSubscribe(d: Disposable?) {
//                }
//
//                override fun onNext(value: Any?) {
//                    useRxJava.testUse()
//                }
//
//                override fun onError(e: Throwable?) {
//                }
//
//                override fun onComplete() {
//                }
//            })
        RxJavaPlugins.setIoSchedulerHandler { t ->
            Log.d(TAG, "apply: 全局 监听 scheduler$t")
            t
        }
        RxJavaPlugins.setInitIoSchedulerHandler { t ->
            Log.d(TAG, "apply: 全局 监听 scheduler$t")
            t.get()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_test_1 -> {
                useRxJava.testUse()
            }
        }
    }
}