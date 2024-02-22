package com.shabban.texttoimage.Data.di.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMzIyMGY4NDUtN2UwMS00ZDY0LWE5ZjktYTgwN2E3NWQxNmU5IiwidHlwZSI6ImFwaV90b2tlbiJ9.Mf8aLKgFQxTn7eW-TIzd5fyTrlakdILufHShrd7c74k"
        )
        return chain.proceed(requestBuilder.build())
    }
}