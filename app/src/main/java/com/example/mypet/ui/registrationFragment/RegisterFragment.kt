package com.example.mypet.ui.registrationFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mypet.R
import com.example.mypet.data.repository.MainRepositoryImpl
import com.example.mypet.databinding.FragmentRegisterBinding
import com.example.mypet.domain.entity.Auth
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.Register
import com.example.mypet.domain.entity.TokenAuth
import com.example.mypet.presentation.registrationFragment.RegisterState
import com.example.mypet.presentation.registrationFragment.RegisterViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            render(state)
        }

        if (viewModel.getToken().isNotEmpty()){
            viewModel.auth(Login(Auth("",""), TokenAuth(viewModel.getToken())))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signTv.setOnClickListener {
            findNavController().navigate(R.id.authFragment)
        }

        binding.regBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val password1 = binding.password1Et.text.toString()

            viewModel.register(
                Register(
                    Auth(email, password),
                    password1
                )
            )
        }
    }

    private fun render(state: RegisterState) {
        when (state) {
            is RegisterState.Error -> {
                binding.progressCircular.isVisible = false
                binding.errorTv.text = state.msg
            }

            RegisterState.Loading -> {
                binding.progressCircular.isVisible = true
            }

            is RegisterState.Success -> {
                binding.progressCircular.isVisible = false
                Log.i("SUCCESS", state.data.toString())
                findNavController().navigate(R.id.action_registerFragment_to_petsFragment)
            }
        }
    }
}