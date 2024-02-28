package com.shabban.texttoimage.Domain.db_use_case

import androidx.lifecycle.LiveData
import com.shabban.texttoimage.Data.room.ImageEntity
import com.shabban.texttoimage.Data.room.ImagesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllImageFromDBUseCase @Inject constructor(
    val imagesDao: ImagesDao
) {

     suspend fun getAllImages() : Flow<List<ImageEntity>> {
        return imagesDao.getImages()

    }


}