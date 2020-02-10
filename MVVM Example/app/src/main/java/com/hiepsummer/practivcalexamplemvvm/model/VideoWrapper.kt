package com.hiepsummer.practivcalexamplemvvm.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoWrapper(
    @Json(name = "data")
    var video: MutableList<Video>? = null,

    @Json(name = "result")
    var result: Int? = null,

    @Json(name = "message")
    var message: String? = null
)