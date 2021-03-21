package com.hulk.androidstudy

import com.hulk.common.base.BaseApplication
import com.hulk.common.loadsir.*
import com.kingja.loadsir.core.LoadSir

class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(TimeoutCallback())
            .addCallback(CustomCallback())
            .setDefaultCallback(LoadingCallback::class.java)
            .commit()
    }
}