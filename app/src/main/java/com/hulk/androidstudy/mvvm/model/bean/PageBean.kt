package com.hulk.androidstudy.mvvm.model.bean

data class PageBean(
    var allPages: Int,
    var contentlist: MutableList<NewsBean>?,
    var currentPage: Int,
    var allNum: Int,
    var maxResult: Int,
)