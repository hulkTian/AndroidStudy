# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# 第一步：最低支持版本
cmake_minimum_required(VERSION 3.10.2)

#生成的项目名称，可以不写使用
project("nativelib")

# 第二步：批量倒入源文件，可以是自己的写的源文件也可以是第三方框架的源文件
file(GLOB SOURCE *.cpp *.c *.h)
# 把源文件打包到指定的库
add_library( # Sets the name of the library.
        nativelib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${SOURCE})

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

# 第三步：指定依赖的库文件。
# 如果是系统提供的公共NDK库，可以直接使用find_library来指定，CMake可以从系统的NDK路径中搜索系统库，并保存起来。
# 系统提供的公共NDK库有哪些？
# CMake是怎么知道在哪个路径查找库文件的？
# 如果是但三方库，可以使用
find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        nativelib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})