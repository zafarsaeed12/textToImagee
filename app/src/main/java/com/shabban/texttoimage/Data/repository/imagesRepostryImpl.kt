package com.shabban.texttoimage.Data.repository

import android.util.Log
import com.shabban.texttoimage.Common.ResponseState
import com.shabban.texttoimage.Data.Api.ApiService
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Data.model.response.ImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class imagesRepostryImpl :imagesRepositry {
    override fun getImagesResponse(request: ImageRequest): Flow<ResponseState<ImageResponse>> = flow{
     emit(ResponseState.Loading)
        try {
            val request = ApiService().imagesService.getImages(request)
            val body: ImageResponse? = request.body()
            Log.e("TESTTG", "${request.body()}")
            Log.e("TESTTG", "${request.message()}")
            Log.e("TESTTG", "${request.headers()}")
            Log.e("TESTTG", "${request.code()}")
            Log.e("TESTTG", "${request.errorBody().toString()}")
            body?.let {
                emit(ResponseState.Success(it))
            } ?: emit(ResponseState.Error(Throwable("Empty Response")))

        } catch (ex: Exception) {
            val message = ex.message?.let {
                it
            } ?: "Unknown exception"
            emit(
                ResponseState.Error(
                    Throwable(message)
                )
            )
        }


    }.flowOn(Dispatchers.IO)


}