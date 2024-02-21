package com.shabban.texttoimage.Data.model.request

data class ImageRequest (
    val attributes_as_list: Boolean,
    val num_images: Int,
    val providers: String,
    val resolution: String,
    val response_as_dict: Boolean,
    val show_original_response: Boolean,
    val text: String
)