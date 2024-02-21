package com.shabban.texttoimage.presentation.models

data class Message (
    val choices: List<Choice>,
    val message: String = "",

    )


data class Choice(
    val finish_reason: String,
    val index: Int,
    val logprobs: Any,
    val text: String
)