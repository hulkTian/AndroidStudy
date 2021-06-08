package com.hulk.androidstudy.mvvm.model.bean

data class NewsBean(
    var allList: MutableList<String>?,
    var pubDate: String,//2016-07-14 11:36:05:
    var title: String,
    var channelName: String,
    var imageurls: String,
    var desc: String,
    var source: String,
    var channelId: String,
    var nid: String,
    var link: String,
)