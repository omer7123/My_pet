package com.example.mypet.ui.petsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypet.R
import com.example.mypet.databinding.FragmentPetsBinding
import com.example.mypet.domain.entity.PetEntity

class PetsFragment : Fragment() {
    private lateinit var binding: FragmentPetsBinding
    private val list = listOf(
        PetEntity("0", "Саня"),
        PetEntity("0", "Саня"),
        PetEntity("0", "Саня"),
        PetEntity("0", "Саня"),
        PetEntity("0", "Саня")
    )

    private val adapter = PetAdapter(this::clickListener)

    private fun clickListener(petEntity: PetEntity) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPetsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.petRv.layoutManager = LinearLayoutManager(requireContext())
        binding.petRv.adapter = adapter
        adapter.submitList(list)
    }
}