package com.example.mypet.ui.detailPetFragment

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.mypet.R
import com.example.mypet.databinding.FragmentDetailPetBinding
import com.example.mypet.domain.entity.PetEntity
import com.example.mypet.ui.petsFragment.PetAdapter

class DetailPetFragment : Fragment() {
    private lateinit var binding: FragmentDetailPetBinding
    private lateinit var pet: PetEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailPetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            pet = (this.getSerializable("res") as PetEntity)
            binding.titleTv.text = pet.name
            if (pet.breed.isNullOrEmpty()) {
                binding.breedTv.text = "Порода: не указано"
            } else binding.breedTv.text = "Порода: ${pet.breed}"

            if (pet.yo==null) {
                binding.ageTv.text = "Возраст: не указано"
            } else binding.ageTv.text = "Возраст: ${pet.yo} лет"

            if (pet.weight==null) {
                binding.weightTv.text = "Вес: не указано"
            } else binding.weightTv.text = "Вес: ${pet.weight} кг"

            if (pet.height==null) {
                binding.heightTv.text = "Рост: не указано"
            } else binding.heightTv.text = "Рост: ${pet.height} см"
        }
    }

}