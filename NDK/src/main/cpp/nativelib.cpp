#include <string>
#include <jni.h>
#include "c_language/chapter_one.h"
//定义全局变量
extern char *content;
/*******************************C语言***************************************/

/**
 * 运行C的main函数，认识C的文件基本结构
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_com_android_ndk_c_1language_chapter_11_NativeChapterOne_runMain(JNIEnv *env, jobject thiz) {
    currentMethod = RUN_MAIN;
    main();
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
    return env->NewStringUTF(content);
}
/*******************************C语言***************************************/