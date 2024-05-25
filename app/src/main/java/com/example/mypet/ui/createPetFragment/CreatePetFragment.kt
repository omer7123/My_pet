package com.example.mypet.ui.createPetFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.mypet.R
import com.example.mypet.databinding.FragmentCreatePetBinding
import com.example.mypet.databinding.FragmentRefactorPetBinding
import com.example.mypet.domain.entity.NewPet
import com.example.mypet.domain.entity.Owner
import com.example.mypet.presentation.createPetFragment.CreatePetState
import com.example.mypet.presentation.createPetFragment.CreatePetViewModel
import com.example.mypet.presentation.refactorPetFragment.RefactorPetViewModel
import com.example.mypet.util.showToast
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePetFragment : Fragment() {
    private lateinit var binding: FragmentCreatePetBinding

    private val viewModel: CreatePetViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePetBinding.inflate(layoutInflater, container, false)
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            render(state)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genderList = listOf("мужской", "женский")

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            genderList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = adapter

        initListener()
    }

    private fun initListener() {
        binding.addBtn.setOnClickListener {
            val name = binding.nameTv.text.toString()
            val age = binding.ageTv.text.toString().toInt()
            val gender = binding.genderSpinner.selectedItem.toString()

            viewModel.addPet(NewPet(name, age, gender, Owner("")))
        }
    }

    private fun render(state: CreatePetState) {
        when (state) {
            is CreatePetState.Error -> {
                binding.progressCircular.isVisible = false
                requireContext().showToast(state.msg)
            }

            CreatePetState.Loading -> {
                binding.progressCircular.isVisible = true
            }

            is CreatePetState.Success -> {
                binding.progressCircular.isVisible = false
                findNavController().popBackStack()
            }
        }
    }

}