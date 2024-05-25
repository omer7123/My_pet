package com.example.mypet.di

import com.example.mypet.presentation.authFragment.AuthViewModel
import com.example.mypet.presentation.registrationFragment.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        RegisterViewModel(
            get()
        )
    }

    viewModel {
        AuthViewModel(
            get()
        )
    }
}