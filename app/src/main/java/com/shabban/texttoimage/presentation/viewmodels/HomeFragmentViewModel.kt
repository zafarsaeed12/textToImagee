package com.shabban.texttoimage.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Domain.GenerateImageUsecase
import com.shabban.texttoimage.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val generateImageUsecase: GenerateImageUsecase
) : ViewModel() {
    val TAG = "HomeFragmentViewModelTAG"
    suspend fun generateImage(imageRequest: ImageRequest) = generateImageUsecase.execute(imageRequest)

}