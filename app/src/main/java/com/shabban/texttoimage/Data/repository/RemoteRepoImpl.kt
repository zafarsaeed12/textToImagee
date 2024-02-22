package com.shabban.texttoimage.Data.repository

import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Data.model.response.ImageResponse
import com.shabban.texttoimage.Data.remote.ApiService
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    val apiService: ApiService
) : RemoteRepo {
    override fun generateImage(imageRequest: ImageRequest): Observable<Response<ImageResponse>> {
        return apiService.generateImage(imageRequest)
    }

}