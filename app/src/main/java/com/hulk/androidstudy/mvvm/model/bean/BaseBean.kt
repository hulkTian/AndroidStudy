package com.hulk.androidstudy.mvvm.model.bean

data class BaseBean(
    var showapi_res_code: Int,
    var showapi_res_error: String,
    var showapi_res_body: ResultBean?
)

data class ResultBean(
    var ret_code: Int,
    var pagebean: PageBean
)

