package com.example.mypet.ui.authFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.mypet.R
import com.example.mypet.databinding.FragmentAuthBinding
import com.example.mypet.domain.entity.Auth
import com.example.mypet.domain.entity.Login
import com.example.mypet.domain.entity.TokenAuth
import com.example.mypet.presentation.authFragment.AuthState

import com.example.mypet.presentation.authFragment.AuthViewModel
import com.example.mypet.presentation.registrationFragment.RegisterState
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    private val viewModel: AuthViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)

        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.regTv.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        binding.signBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            viewModel.auth(Login(Auth(email, password), TokenAuth("")))
        }
    }

    private fun render(it: AuthState) {
        when (it) {
            is AuthState.Error -> {
                binding.progressCircular.isVisible = false
                binding.errorTv.text = it.msg
            }

            AuthState.Loading -> {
                binding.progressCircular.isVisible = true
            }
            is AuthState.Success -> findNavController().navigate(R.id.action_authFragment_to_petsFragment)
        }
    }

}