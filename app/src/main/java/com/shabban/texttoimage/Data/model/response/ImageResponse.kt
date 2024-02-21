package com.shabban.texttoimage.Data.model.response

data class ImageResponse(
    val amazon: Amazon,
    val deepai: Deepai,
    val openai: Openai,
    val replicate: Replicate,
    val stabilityai: Stabilityai
)