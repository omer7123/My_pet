package com.example.mypet.di


import org.koin.dsl.module

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideRetrofit(get()) }
    factory { provideApi(get()) }
}