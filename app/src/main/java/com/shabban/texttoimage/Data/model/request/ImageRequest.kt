package com.shabban.texttoimage.Data.model.request

data class ImageRequest (
    val show_original_response: Boolean?,
    val fallback_providers: String?,
    val providers: String?,
    val text: String?,
    val resolution: String?,
    val num_images: Int?
)