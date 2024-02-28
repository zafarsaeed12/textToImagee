package com.shabban.texttoimage.Domain.db_use_case

import com.shabban.texttoimage.Data.room.ImageEntity
import com.shabban.texttoimage.Data.room.ImagesDao
import javax.inject.Inject

class InsertImageUseCase @Inject constructor(
    private val dao: ImagesDao
) {
    suspend fun insertImage(imageEntity: ImageEntity) {
        dao.insertImage(imageEntity)
    }
}