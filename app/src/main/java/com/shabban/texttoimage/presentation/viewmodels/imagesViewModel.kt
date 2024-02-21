package com.shabban.texttoimage.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shabban.texttoimage.Common.ResponseState
import com.shabban.texttoimage.Common.UiState
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Data.model.response.ImageResponse
import com.shabban.texttoimage.Data.repository.imagesRepositry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class imagesViewModel(val imagesRepositry: imagesRepositry) : ViewModel() {
    private var _chatState = MutableStateFlow<UiState<ImageResponse>>(UiState.Loading)
    val chatState = _chatState.asStateFlow()


    fun getresponse(boolean: Boolean) {
        val request = ImageRequest(boolean,1,"null","null",true,true,"null")

        imagesRepositry.getImagesResponse(request).onEach {

            when (it) {
                is ResponseState.Error -> _chatState.value = UiState.Error(it.throwable)
                ResponseState.Loading -> _chatState.value = UiState.Loading
                is ResponseState.Success -> {
                    _chatState.value = UiState.Success(it.item)
                }
            }

        }.catch {
            _chatState.value = UiState.Error(
                Throwable("Upstream exception")
            )
        }.launchIn(
            viewModelScope
        )
    }


}

class ChatViewModelFactory constructor(private val repository: imagesRepositry): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(imagesViewModel::class.java!!)) {
            imagesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}