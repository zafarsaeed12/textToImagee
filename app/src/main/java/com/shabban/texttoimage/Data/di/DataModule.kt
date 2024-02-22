package com.shabban.texttoimage.Data.di

import com.shabban.texttoimage.Data.remote.ApiService
import com.shabban.texttoimage.Data.repository.RemoteRepo
import com.shabban.texttoimage.Data.repository.RemoteRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRemoteRepo(
        apiService: ApiService
    ): RemoteRepo {
        return RemoteRepoImpl(apiService)
    }
}