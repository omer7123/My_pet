package com.example.mypet.di

import com.example.mypet.data.repository.MainRepositoryImpl
import com.example.mypet.domain.MainRepository
import org.koin.dsl.module

val repoModules = module {
    single<MainRepository>{ MainRepositoryImpl(get(), get()) }
}