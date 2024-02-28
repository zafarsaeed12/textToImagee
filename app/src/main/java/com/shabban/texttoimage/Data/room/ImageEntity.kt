package com.shabban.texttoimage.Data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "generatedImages")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageUrl: String?,
    val imageBase64: String?
)
