package com.example.mypet.ui.registrationFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypet.R
import com.example.mypet.data.repository.MainRepository
import com.example.mypet.databinding.ActivityMainBinding
import com.example.mypet.databinding.FragmentRegisterBinding
import com.example.mypet.domain.entity.Auth
import com.example.mypet.domain.entity.Register
import org.koin.android.ext.android.inject

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val repository: MainRepository by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signTv.setOnClickListener {
            findNavController().navigate(R.id.authFragment)
        }
        binding.regBtn.setOnClickListener {
            repository.register(Register(Auth("ilyafomin12@gmail.com", "fdfd123456789"),"fdfd123456789")).observe(viewLifecycleOwner){
                Log.i("FDFD", "GFGFGF")
            }
        }
    }
}