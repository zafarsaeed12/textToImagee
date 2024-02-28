package com.shabban.texttoimage.Data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(imageEntity: ImageEntity)

    @Delete
    fun deleteImage(imageEntity: ImageEntity)

    @Query("SELECT * FROM generatedImages")
    fun getImages(): Flow<List<ImageEntity>>

}