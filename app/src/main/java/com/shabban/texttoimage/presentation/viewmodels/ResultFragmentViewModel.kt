package com.shabban.texttoimage.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Domain.GenerateImageUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultFragmentViewModel @Inject constructor(
    private val generateImageUseCase: GenerateImageUsecase
) : ViewModel() {

    suspend fun generateImage(imageRequest: ImageRequest) =
        generateImageUseCase.execute(imageRequest)


}