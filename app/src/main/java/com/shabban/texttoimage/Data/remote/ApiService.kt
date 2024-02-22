package com.shabban.texttoimage.Data.remote

import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Data.model.response.ImageResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST ("image/generation")
    fun generateImage(
        @Body request : ImageRequest
    ) : Observable<Response<ImageResponse>>
}