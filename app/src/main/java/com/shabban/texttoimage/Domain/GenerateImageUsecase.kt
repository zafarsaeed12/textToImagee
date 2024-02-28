package com.shabban.texttoimage.Domain

import com.shabban.texttoimage.Data.di.IoDispatcher
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Data.model.response.ImageResponse
import com.shabban.texttoimage.Data.repository.RemoteRepo
import com.shabban.texttoimage.Utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenerateImageUsecase @Inject constructor(
    private val remoteRepo: RemoteRepo,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun execute(imageRequest: ImageRequest) = callbackFlow<Resource<ImageResponse?>> {
        trySend(Resource.Loading())

        remoteRepo.generateImage(imageRequest).subscribe({ response ->
            if (response.isSuccessful && response.body() != null) {
                if (response.body()?.stabilityai?.status.equals("fail")) {
                    trySend(Resource.UnSuccess("Something went wrong, status failed."))
                } else {
                    trySend(Resource.Success(response.body()))
                }
            } else {
                val error = response?.body()?.stabilityai?.status
                trySend(Resource.UnSuccess(error))
            }
        }, { failed ->
            val errorMessage = failed?.message
            trySend(Resource.Error(errorMessage))
        })
        awaitClose()
    }.flowOn(coroutineDispatcher)
}