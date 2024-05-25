package com.example.mypet.di

import com.example.mypet.presentation.authFragment.AuthViewModel
import com.example.mypet.presentation.createPetFragment.CreatePetViewModel
import com.example.mypet.presentation.refactorPetFragment.RefactorPetViewModel
import com.example.mypet.presentation.petsFragment.PetsViewModel
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
    viewModel {
        PetsViewModel(
            get()
        )
    }

    viewModel {
        RefactorPetViewModel(
            get()
        )
    }

    viewModel {
        CreatePetViewModel(
            get()
        )
    }
}