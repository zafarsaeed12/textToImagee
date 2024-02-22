package com.shabban.texttoimage.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shabban.texttoimage.Data.model.response.ImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeSharedViewModel @Inject constructor() : ViewModel() {


    var _imageUrlLiveData = MutableLiveData<ImageResponse>()
    var imageUrlLiveData: LiveData<ImageResponse> = _imageUrlLiveData

    fun setImageData(data: ImageResponse) {
        _imageUrlLiveData.postValue(data)
    }

}