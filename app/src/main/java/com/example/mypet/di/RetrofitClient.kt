package com.example.mypet.di

import com.example.mypet.data.remote.PetApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
}



fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://af32-176-209-183-6.ngrok-free.app")
        .build()

}

fun provideApi(retrofit: Retrofit): PetApi {
    return retrofit.create(PetApi::class.java)
}

