package com.example.mypet.di

import com.example.mypet.presentation.authFragment.AuthViewModel
import com.example.mypet.presentation.createPetFragment.CreatePetViewModel
import com.example.mypet.presentation.createTaskFragment.CreateTaskViewModel
import com.example.mypet.presentation.detailPetFragment.DetailPetViewModel
import com.example.mypet.presentation.homeFragment.HomeViewModel
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

    viewModel {
        DetailPetViewModel(
            get()
        )
    }

    viewModel {
        HomeViewModel(
            get()
        )
    }

    viewModel {
        CreateTaskViewModel(
            get()
        )
    }
}