package com.example.mypet.ui.petsFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypet.R
import com.example.mypet.databinding.FragmentPetsBinding
import com.example.mypet.domain.entity.PetEntity

class PetsFragment : Fragment() {

    private lateinit var binding: FragmentPetsBinding
    private val list = mutableListOf(
        PetEntity("0", "Саня", "Корги", 20, 40, 190),
        PetEntity("1", "Илья", "Мопс", 19, 64, 172),
        PetEntity("2", "Макс", "Корейская овчарка", 19, 59, 120),
        PetEntity("3", "Иса", "Бегемотская", 19, 89, 169),
        PetEntity("4", "Паша", "Русская борзая", 19, 75, 177)
    )

    private val adapter = PetAdapter(this::clickListener)

    private fun clickListener(petEntity: PetEntity) {
        val bundle = Bundle()
        bundle.putSerializable("res", petEntity)
        findNavController().navigate(R.id.detailPetFragment, bundle)
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
        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.createPetFragment)
        }

        setFragmentResultListener("res") { _, result ->
            val pet = result.getSerializable("res") as PetEntity
            list.add(pet)
            adapter.submitList(list)
            Log.i("dsds", list.toString())
        }
    }
}