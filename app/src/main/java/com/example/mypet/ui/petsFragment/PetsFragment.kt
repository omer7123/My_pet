package com.example.mypet.ui.petsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypet.R
import com.example.mypet.databinding.FragmentPetsBinding
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.presentation.petsFragment.PetsState
import com.example.mypet.presentation.petsFragment.PetsViewModel
import com.example.mypet.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PetsFragment : Fragment() {

    private lateinit var binding: FragmentPetsBinding

    private val viewModel: PetsViewModel by viewModel()


    private val adapter = PetAdapter(this::clickListener)

    private fun clickListener(petEntity: PetItem) {
        val bundle = Bundle()
        bundle.putSerializable("res", petEntity)
        findNavController().navigate(R.id.action_petsFragment_to_detailPetFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPetsBinding.inflate(layoutInflater, container, false)

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            render(state)
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.petRv.layoutManager = LinearLayoutManager(requireContext())
        binding.petRv.adapter = adapter

        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_petsFragment_to_createPetFragment)
        }
        viewModel.getPets()

//        setFragmentResultListener("res") { _, result ->
//            val pet = result.getSerializable("res") as PetEntity
//            list.add(pet)
//            adapter.submitList(list)
//        }
    }

    private fun render(state: PetsState) {
        when (state) {
            is PetsState.Error -> {
                binding.progressCircular.isVisible = false
                requireContext().showToast(state.msg)
            }

            PetsState.Loading -> {
                binding.progressCircular.isVisible = true
            }

            is PetsState.Success -> {
                binding.progressCircular.isVisible = false
                adapter.submitList(state.data)
            }
        }
    }
}