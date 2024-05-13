package com.example.mypet.ui.createPetFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.mypet.R
import com.example.mypet.databinding.FragmentCreatePetBinding
import com.example.mypet.domain.entity.PetEntity
import java.util.UUID

class CreatePetFragment : Fragment() {
   private lateinit var binding: FragmentCreatePetBinding
   private val uuid = UUID.randomUUID()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uuidString = uuid.toString()

        binding.addBtn.setOnClickListener {
            val name = binding.nameTv.text.toString()
            val breed = binding.breedTv.text.toString()
            val age = binding.ageTv.text.toString()
            val weight = binding.weightTv.text.toString()
            val height = binding.heightTv.text.toString()

            val ageInt = if (age.isNotEmpty()) age.toIntOrNull() else null
            val weightInt = if (weight.isNotEmpty()) weight.toIntOrNull() else null
            val heightInt = if (height.isNotEmpty()) height.toIntOrNull() else null

            val result = PetEntity(uuidString, name, breed, ageInt, weightInt, heightInt)
            setFragmentResult("res", bundleOf("res" to result))
            findNavController().popBackStack()
        }

    }

}