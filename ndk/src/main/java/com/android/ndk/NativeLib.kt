package com.android.ndk

class NativeLib {

    /**
     * A native method that is implemented by the 'ndk' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'ndk' library on application startup.
        init {
            System.loadLibrary("ndk")
        }
    }
}