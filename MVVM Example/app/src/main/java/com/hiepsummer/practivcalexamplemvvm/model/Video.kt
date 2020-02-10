package com.hiepsummer.practivcalexamplemvvm.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Video(

    @Json(name = "id")
    var id: Int? = 0,

    @Json(name = "name")
    var name: String? = null,

    @Json(name = "uri")
    var uri: String? = null,

    @Json(name = "type")
    var type: String? = null
)