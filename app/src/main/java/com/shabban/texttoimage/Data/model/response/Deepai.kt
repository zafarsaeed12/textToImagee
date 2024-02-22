package com.shabban.texttoimage.Data.model.response

import com.google.gson.annotations.SerializedName

data class Deepai(
    @SerializedName("cost")
    var cost: Double?,
    @SerializedName("items")
    var items: List<Item?>?,
    @SerializedName("status")
    var status: String?
)