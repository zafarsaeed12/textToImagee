package com.shabban.texttoimage.Utils

sealed class Resource<T>(val message: String? = null) {
     class Loading<T> : Resource<T>()
     class Success<T>(val data: T) : Resource<T>()
     class Error<T>(val errorMessage: String?) : Resource<T>()
     class UnSuccess<T>(val unSuccessMessage: String?) : Resource<T>()

}
