package com.shabban.texttoimage.Data.repository

import com.shabban.texttoimage.Common.ResponseState
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Data.model.response.ImageResponse
import kotlinx.coroutines.flow.Flow

interface imagesRepositry {

public fun getImagesResponse(request: ImageRequest) : Flow<ResponseState<ImageResponse>>
}