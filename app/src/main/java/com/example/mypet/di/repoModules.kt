package com.example.mypet.di

import com.example.mypet.data.repository.MainRepository
import org.koin.dsl.module

val repoModules = module {
    single<MainRepository>{ MainRepository(get()) }
}