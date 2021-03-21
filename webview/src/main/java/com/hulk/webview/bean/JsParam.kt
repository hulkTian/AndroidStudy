package com.hulk.webview.bean

import com.google.gson.JsonObject

data class JsParam(
    var name: String? = null,
    var param: JsonObject? = null,
)