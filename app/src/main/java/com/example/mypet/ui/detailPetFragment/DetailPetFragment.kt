package com.example.mypet.ui.detailPetFragment

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.mypet.R
import com.example.mypet.databinding.FragmentDetailPetBinding
import com.example.mypet.domain.entity.PetEntity
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.ui.petsFragment.PetAdapter

class DetailPetFragment : Fragment() {
    private lateinit var binding: FragmentDetailPetBinding
    private lateinit var pet: PetItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailPetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundleForRefactor = Bundle()
        binding.refactorBtn.setOnClickListener {
            bundleForRefactor.putSerializable("res", pet)
            findNavController().navigate(R.id.refactorPetFragment, bundleForRefactor)
        }

        arguments?.run {
            pet = (this.getSerializable("res") as PetItem)
            binding.titleTv.text = pet.name
            if (pet.breed_id.isEmpty()) {
                binding.breedTv.text = "Порода: не указано"
            } else binding.breedTv.text = "Порода: ${pet.breed_id}"

            if (pet.age.toString().isEmpty()) {
                binding.ageTv.text = "Возраст: не указано"
            } else binding.ageTv.text = "Возраст: ${pet.age} лет"

            if (pet.weight==0) {
                binding.weightTv.text = "Вес: не указано"
            } else binding.weightTv.text = "Вес: ${pet.weight} кг"

            if (pet.height==0) {
                binding.heightTv.text = "Рост: не указано"
            } else binding.heightTv.text = "Рост: ${pet.height} см"
        }
    }

}