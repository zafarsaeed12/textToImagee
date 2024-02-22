package com.shabban.texttoimage.Data.model.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("deepai")
    var deepai: Deepai?,
    @SerializedName("openai")
    var openai: Openai?,
    @SerializedName("stabilityai")
    var stabilityai: Stabilityai?
)