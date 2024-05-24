package com.example.mypet.di

import com.example.mypet.data.remote.RemoteDataSource
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}