package com.shabban.texttoimage.Data.Api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
	
	
	val baseUrl = "https://api.edenai.run/v2/image/generation"
	private fun getOkHttpClient(): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(AuthInterceptor())
			.build()
	}
	fun getInstance(): Retrofit {
		return Retrofit.Builder().baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create())
			.client(getOkHttpClient())
			.build()
	}
}