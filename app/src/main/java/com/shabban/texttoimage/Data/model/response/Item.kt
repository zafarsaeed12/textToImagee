package com.shabban.texttoimage.Data.model.response

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("image")
    var image: String?,
    @SerializedName("image_resource_url")
    var imageResourceUrl: String?
)