package com.shabban.texttoimage.Data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.shabban.texttoimage.Data.room.ImagesDB
import com.shabban.texttoimage.Data.room.ImagesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideImagesDB(@ApplicationContext appContext: Context): ImagesDB {
        return Room.databaseBuilder(
            appContext,
            ImagesDB::class.java,
            "images_database"
        ).build()
    }
    @Provides
    fun provideImagesDao(database: ImagesDB): ImagesDao {
        return database.imageDao()
    }


}