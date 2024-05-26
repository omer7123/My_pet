package com.example.mypet.ui.detailPetFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.mypet.R
import com.example.mypet.databinding.FragmentDetailPetBinding
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.presentation.detailPetFragment.DetailPetState
import com.example.mypet.presentation.detailPetFragment.DetailPetViewModel
import com.example.mypet.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPetFragment : Fragment() {
    private lateinit var binding: FragmentDetailPetBinding
    private lateinit var pet: PetItem

    private val viewModel: DetailPetViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailPetBinding.inflate(layoutInflater, container, false)

        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundleForRefactor = Bundle()
        binding.refactorBtn.setOnClickListener {
            bundleForRefactor.putSerializable("res", pet)
            findNavController().navigate(R.id.action_detailPetFragment_to_refactorPetFragment, bundleForRefactor)
        }

        arguments?.run {
            pet = (this.getSerializable("res") as PetItem)

            viewModel.getDetailPet(pet.id)
        }
    }

    private fun render(it: DetailPetState) {
        when (it) {
            is DetailPetState.Error -> {
                binding.progressCircular.isVisible = false
                requireContext().showToast(it.msg)
            }

            DetailPetState.Loading -> binding.progressCircular.isVisible = true


            is DetailPetState.Content -> {
                renderSuccess(it.data, it.breed, it.animal)

            }
        }
    }

    private fun renderSuccess(pet: PetItem, breed: String, animal: String) {
        binding.progressCircular.isVisible = false

        if (breed.isEmpty()) {
            binding.breedTv.text = "Порода: не указано"
        } else binding.breedTv.text = "Порода: ${breed}"

        if (animal.isEmpty()) {
            binding.animalTv.text = "Вид: не указано"
        } else binding.animalTv.text = "Вид: ${animal}"

        if (pet.gender.isEmpty()) {
            binding.genderTv.text = "Пол: не указано"
        } else binding.genderTv.text = "Пол: ${pet.gender}"

        binding.titleTv.text = pet.name



        if (pet.age.toString().isEmpty()) {
            binding.ageTv.text = "Возраст: не указано"
        } else binding.ageTv.text = "Возраст: ${pet.age} лет"

        if (pet.weight == 0) {
            binding.weightTv.text = "Вес: не указано"
        } else binding.weightTv.text = "Вес: ${pet.weight} кг"

        if (pet.height == 0) {
            binding.heightTv.text = "Рост: не указано"
        } else binding.heightTv.text = "Рост: ${pet.height} см"

    }

}