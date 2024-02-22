package com.shabban.texttoimage.Data.di

import com.google.gson.GsonBuilder
import com.shabban.texttoimage.Data.remote.ApiService
import com.shabban.texttoimage.Data.di.interceptor.AuthInterceptor
import com.shabban.texttoimage.Data.repository.RemoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        var mGsonConverter: GsonConverterFactory? = null
        mGsonConverter = GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .disableHtmlEscaping()
                .create()
        )

        val loggingInterceptor = HttpLoggingInterceptor()

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.edenai.run/v2/")
            .addConverterFactory(mGsonConverter)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}