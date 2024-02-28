package com.shabban.texttoimage.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shabban.texttoimage.Domain.db_use_case.GetAllImageFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreationFragmentViewModel @Inject constructor(
    private val getAllImageFromDBUseCase: GetAllImageFromDBUseCase
) :ViewModel(){
    suspend fun getImagesFromDB() = getAllImageFromDBUseCase.getAllImages()

}