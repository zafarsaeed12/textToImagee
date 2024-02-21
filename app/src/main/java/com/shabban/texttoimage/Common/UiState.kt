package com.shabban.texttoimage.Common

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Error(val throwable: Throwable) : UiState<Nothing>()
    data class Success<T>(val item: T) : UiState<T>()
}