#include <jni.h>
#include <string>
#include <jni.h>
#include "c_language/chapter_one.h"


extern "C"
JNIEXPORT jstring JNICALL
Java_com_android_ndk_c_1language_chapter_11_NativeChapterOne_runMain(JNIEnv *env, jobject thiz) {
    main();
    //std::string hello = "Hello from C++";
    return env->NewStringUTF(content);
}