package com.shabban.texttoimage.Data.Api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
	
	override fun intercept(chain: Interceptor.Chain): Response = chain.run {
		proceed(
			request()
				.newBuilder()
				.addHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNTYzZDJkZWUtN2I3Ni00ZjQ5LTg0ZGMtZGJmNTc3ZmJhMWVmIiwidHlwZSI6ImFwaV90b2tlbiJ9.QvCku03D_PlEmAEPuIoyogz2WGbcjx__4Qwm5s-Jdqc")
				.build()
		)
	}
	//need api key
}