package com.example.mypet.ui.refactorPetFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.mypet.databinding.FragmentRefactorPetBinding
import com.example.mypet.domain.entity.Animal
import com.example.mypet.domain.entity.PetEntity
import com.example.mypet.presentation.refactorPetFragment.RefactorPetState
import com.example.mypet.presentation.refactorPetFragment.RefactorPetViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RefactorPetFragment : Fragment() {

    private lateinit var binding: FragmentRefactorPetBinding

    private val viewModel: RefactorPetViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAnimals()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRefactorPetBinding.inflate(layoutInflater, container, false)

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            render(state)
        }
        return binding.root
    }

    private fun render(state: RefactorPetState) {
        when (state) {
            is RefactorPetState.Error -> {}
            RefactorPetState.Loading -> {}
            is RefactorPetState.SuccessAnimal -> {
                Log.e("Animals", state.animals.toString())
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    state.animals
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.animalSpinner.adapter = adapter
            }

            is RefactorPetState.SuccessBreed -> {
                Log.e("Breeds", state.breeds.toString())
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    state.breeds
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerBreed.adapter = adapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListenerSpinnerAnimals()
        initListener()

    }

    private fun initListenerSpinnerAnimals() {
        binding.animalSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position) as Animal
                Log.e("breeds", viewModel.getBreeds(selectedItem.id).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun initListener() {
        binding.addBtn.setOnClickListener {
            val name = binding.nameTv.text.toString()
//            val breed = binding.breedTv.text.toString()
            val age = binding.ageTv.text.toString()
            val weight = binding.weightTv.text.toString()
            val height = binding.heightTv.text.toString()

            val ageInt = if (age.isNotEmpty()) age.toIntOrNull() else null
            val weightInt = if (weight.isNotEmpty()) weight.toIntOrNull() else null
            val heightInt = if (height.isNotEmpty()) height.toIntOrNull() else null

            val result = PetEntity("uuidString", name, "breed", ageInt, weightInt, heightInt)
            setFragmentResult("res", bundleOf("res" to result))
            findNavController().popBackStack()
        }
    }

}