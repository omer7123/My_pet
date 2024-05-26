package com.example.mypet.ui.refactorPetFragment

import android.R
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
import com.example.mypet.domain.entity.Breed
import com.example.mypet.domain.entity.Owner
import com.example.mypet.domain.entity.PetEntity
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.domain.entity.PetItemUpdate
import com.example.mypet.presentation.refactorPetFragment.RefactorPetState
import com.example.mypet.presentation.refactorPetFragment.RefactorPetViewModel
import com.example.mypet.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class RefactorPetFragment : Fragment() {

    private lateinit var binding: FragmentRefactorPetBinding

    private val viewModel: RefactorPetViewModel by viewModel()
    private lateinit var pet: PetItem
    private var selectedIdAnimal: String = ""
    private var selectedIdBreed: String = ""

    private var pet_id = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRefactorPetBinding.inflate(layoutInflater, container, false)

        arguments?.run {
            pet = this.getSerializable("res") as PetItem
            pet_id = pet.id
            binding.nameTv.setText(pet.name)
            binding.ageTv.setText(pet.age.toString())
            binding.heightTv.setText(pet.height.toString())
            binding.weightTv.setText(pet.weight.toString())
            selectedIdAnimal = pet.animal_id
            selectedIdBreed = pet.breed_id

        }
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            render(state)
        }
        return binding.root
    }

    private fun render(state: RefactorPetState) {
        when (state) {
            is RefactorPetState.Error -> requireContext().showToast(state.msg)

            RefactorPetState.Loading -> {}

            is RefactorPetState.SuccessAnimal -> {
                Log.e("Animals", state.animals.toString())
                val adapter = ArrayAdapter(
                    requireContext(),
                    R.layout.simple_spinner_item,
                    state.animals
                )
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                binding.animalSpinner.adapter = adapter

                val selectedIndex = findItemIndex(state.animals, selectedIdAnimal)
                if (selectedIndex >= 0) {
                    binding.animalSpinner.setSelection(selectedIndex)
                }
            }

            is RefactorPetState.SuccessBreed -> {
                Log.e("Breeds", state.breeds.toString())
                val adapter = ArrayAdapter(
                    requireContext(),
                    R.layout.simple_spinner_item,
                    state.breeds
                )
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                binding.spinnerBreed.adapter = adapter

                val selectedIndex = findItemIndexBreed(state.breeds, selectedIdBreed)
                if (selectedIndex >= 0) {
                    binding.spinnerBreed.setSelection(selectedIndex)
                }
            }

            is RefactorPetState.Success -> {
                findNavController().popBackStack()
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListenerSpinnerAnimals()
        initListener()
        viewModel.getAnimals()

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

            try {
                val name = binding.nameTv.text.toString()
//            val breed = binding.breedTv.text.toString()
                val age = binding.ageTv.text.toString()
                val weight = binding.weightTv.text.toString()
                val height = binding.heightTv.text.toString()

//                val ageInt = if (age.isNotEmpty()) age.toIntOrNull() else 0
//                val weightInt = if (weight.isNotEmpty()) weight.toIntOrNull() else 0
//                val heightInt = if (height.isNotEmpty()) height.toIntOrNull() else 0

                val animal = binding.animalSpinner.selectedItem as Animal
                val breed = binding.spinnerBreed.selectedItem as Breed
                val animal_id = animal.id
                val breed_id = breed.id
                val result = PetItemUpdate(
                    pet_id, name, age.toInt(), "Мужской", 0,
                    animal_id, breed_id, weight.toInt(), height.toInt(), "", Owner("")
                )

                viewModel.updatePet(result)
            } catch (e: Exception) {
                requireContext().showToast("Поля с численными данными не могут содержать символы")
            }
//            setFragmentResult("res", bundleOf("res" to result))
//            findNavController().popBackStack()

        }
    }

    private fun findItemIndex(itemList: List<Animal>, id: String): Int {
        return itemList.indexOfFirst { it.id == id }
    }

    private fun findItemIndexBreed(itemList: List<Breed>, id: String): Int {
        return itemList.indexOfFirst { it.id == id }
    }
}