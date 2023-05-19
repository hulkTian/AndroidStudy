# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# 第一步：最低支持版本
cmake_minimum_required(VERSION 3.10.2)

#生成的项目名称，可以不写使用
project("nativelib")

# 第二步：批量倒入源文件，可以是自己的写的源文件也可以是第三方框架的源文件
file(GLOB SOURCE *.cpp *.c *.h ./c_language/*.c ./c_language/*.h)
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
        ${log-lib}
        # 链接到总库
        getndk)

# 打印CMake日志
message(STATUS ">>>>>>>>>>>>>>>>>>>")

# 声明变量并赋值,CMake中所有变量都是String类型
set(var "666")
message(STATUS ">>>>>>>>>>>>>>>>>>>${var}")

# 移除变量
unset(var)
message(STATUS ">>>>>>>>>>>>>>>>>>>${var}")

# 设置条件控制
# true: ON、非零值、TRUE、Y、YES
# false：OFF、0、FALSE、N、NO、IGNORE、NOFOUND
set(switch_off OFF)
set(switch_on ON)

if (${switch_off})
    message(">>>>>>>>>>${switch_off}")
elseif (${switch_on})
    message(">>>>>>>>>>${switch_on}")
else (${switch_off})
    message(">>>>>>>>>>${switch_end}")
endif (${switch_off})

# While循环
set(a "")
while (NOT a STREQUAL "xxx")
    set(a "${a}x")
    message(">>>>>>>>>>${a}")
endwhile ()

# foreach循环，列举每个元素遍历
foreach (item 1 3 2)
    message(">>>>>>>>>>${item}")
endforeach ()

# foreach循环，设置范围遍历
foreach (item RANGE 4)
    message(">>>>>>>>>>${item}")
endforeach ()

# foreach循环，设置范围并设置步长遍历
foreach (item RANGE 1 7 2)
    message(">>>>>>>>>>${item}")
endforeach ()

# foreach循环，遍历list
set(list_a 1 2 3)
foreach (item IN LISTS list_a)
    message(">>>>>>>>>>${item}")
endforeach ()

# 自定义函数
function(num_method n1 n2 n3)
    message("call number method")
    message("n1 = ${n1}")
    message("n2 = ${n2}")
    message("n2 = ${n3}")
    message("ARGC = ${ARGC}")
    message("arg1 = ${ARGV0} arg2 = ${ARGV1} arg3 = ${ARGV2}")
    message("all args = ${ARGV}")
endfunction(num_method)
# 调用函数
num_method(1 2 3)

#常用的常量
message("CMAKE_SOURCE_DIR = ${CMAKE_SOURCE_DIR}")
message("CMAKE_CXX_FLAGS = ${CMAKE_CXX_FLAGS}")
message("CMAKE_C_FLAGS = ${CMAKE_C_FLAGS}")
message("CMAKE_ANDROID_ARCH = ${CMAKE_ANDROID_ARCH}")
message("CMAKE_ANDROID_ARCH_ABI = ${CMAKE_ANDROID_ARCH_ABI}")