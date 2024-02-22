package com.shabban.texttoimage.Data.model.response

import com.google.gson.annotations.SerializedName

data class Openai(
    @SerializedName("cost")
    var cost: Double?,
    @SerializedName("items")
    var items: List<Item>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("provider_status_code")
    var providerStatusCode: Int?
)