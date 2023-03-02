package com.android.ndk.c_language.chapter_1

import com.android.ndk.main.NativeLib

/**
 * @description native 方法声明类
 * @author: zehao.tian
 * @date: 2022/12/7
 */
class NativeChapterOne: NativeLib() {

    external fun runMain():String

    external fun pointAndAddress():String
}