package com.shabban.texttoimage.Data.repository

import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Data.model.response.ImageResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response

interface RemoteRepo {
    fun generateImage(imageRequest: ImageRequest) : Observable<Response<ImageResponse>>
}