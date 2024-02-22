package com.shabban.texttoimage.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shabban.texttoimage.Common.Constant
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Domain.GenerateImageUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultFragmentViewModel @Inject constructor(
    private val generateImageUseCase: GenerateImageUsecase
) : ViewModel() {

    var currentImageUrl : String? = Constant.EMPTY_STRING
    var currentImageBase64 : String? = Constant.EMPTY_STRING

    suspend fun generateImage(imageRequest: ImageRequest) =
        generateImageUseCase.execute(imageRequest)
}