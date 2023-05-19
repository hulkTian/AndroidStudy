#include <string>
#include <jni.h>
#include "c_language/chapter_one.h"
#include <android/log.h>
#define TAG "TS"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__);
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__);
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__);

// 定义一个函数，由程序在调用时自己寻找实现
extern "C" {
    extern int get();
}

//在此文件定义一个用于返回运行结果给java层的内容的字符串
char *content = nullptr;
/*******************************C语言***************************************/

/**
 * 运行C的main函数，认识C的文件基本结构
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_com_android_ndk_c_1language_chapter_11_NativeChapterOne_runMain(JNIEnv *env, jobject thiz) {
    currentMethod = RUN_MAIN;
    main();
    if (content == nullptr) {
        std::strcpy(content, "null");
    }
    LOGD("get:%d\n", get())
    return env->NewStringUTF(content);
}

/**
 * C语言指针和内存地址的关系
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_com_android_ndk_c_1language_chapter_11_NativeChapterOne_pointAndAddress(JNIEnv *env,
                                                                             jobject thiz) {
    currentMethod = RUN_POINT_AND_ADDRESS;
    main();
    if (content == nullptr) {
        std::strcpy(content, "null");
    }
    return env->NewStringUTF(content);
}
/*******************************C语言***************************************/