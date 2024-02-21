package com.shabban.texttoimage.Data.Api

import com.shabban.texttoimage.Common.Constant.END_POINT_COMPLETION
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Data.model.response.ImageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ImagesApi {
	
	@POST(END_POINT_COMPLETION)

	suspend fun getImages(@Body body : ImageRequest): Response<ImageResponse>
}

data class ApiService(val imagesService : ImagesApi= RetrofitHelper.getInstance().create(ImagesApi::class.java))