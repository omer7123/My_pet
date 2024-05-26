package com.example.mypet.ui.detailTaskFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypet.R
import com.example.mypet.databinding.FragmentDetailTaskBinding

class DetailTaskFragment : Fragment() {

    private lateinit var binding: FragmentDetailTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}