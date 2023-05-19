cmake_minimum_required(VERSION 3.10.2)
project("nativelib")

file(GLOB SOURCE ${CMAKE_SOURCE_DIR}/cpp/*.cpp ${CMAKE_SOURCE_DIR}/cpp/*.c
        ${CMAKE_SOURCE_DIR}/cpp/*.h ${CMAKE_SOURCE_DIR}/cpp/c_language/*.c
        ${CMAKE_SOURCE_DIR}/cpp/c_language/*.h)
add_library(nativelib SHARED ${SOURCE})

# 导入预编译的库
# 第一步：导入头文件
#include_directories("${CMAKE_SOURCE_DIR}/cpp/inc")
# 第二步：导入库文件，在CMAKE_CXX_FLAGS上追加一个路径
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${CMAKE_SOURCE_DIR}/jniLibs/${CMAKE_ANDROID_ARCH_ABI}")

# 导入库文件的第二种方式：
# 导入libgetndk动态库
#add_library(getndk SHARED IMPORTED)
#set_target_properties(getndk PROPERTIES IMPORTED_LOCATION
#        ${CMAKE_SOURCE_DIR}/jniLibs/${CMAKE_ANDROID_ARCH_ABI}/libgetndk.so)

#导入libgetndk静态库
#add_library(getndk STATIC IMPORTED)
#根据路径设置链接
#set_target_properties(getndk PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/cpp/libget/libgetndk.a)

find_library(log-lib log)

target_link_libraries(nativelib ${log-lib} getndk)