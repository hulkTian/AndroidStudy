package com.hulk.common.bean

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/24
 */

/**
 * @param className 类的名称
 * @param desc 类的作用
 * @param methodName 方法名称
 * @param methodDesc 方法作用
 * @param code 代码
 * @param canRun 是否能运行
 */
data class ClassInfoBean(
    var className: String,
    var desc: String,
    var methodName: String,
    var methodDesc: String,
    var code: String,
    var canRun:Boolean
)