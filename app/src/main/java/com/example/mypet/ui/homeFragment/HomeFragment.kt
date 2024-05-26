package com.example.mypet.ui.homeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypet.R
import com.example.mypet.databinding.FragmentHomeBinding
import com.example.mypet.domain.entity.Task
import com.example.mypet.presentation.homeFragment.HomeState
import com.example.mypet.presentation.homeFragment.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    private val adapter = TaskAdapter(this::clickAcceptListener, this::clickItemListener)

    private fun clickAcceptListener(task: Task) {
        Log.e("TASK", task.toString())
        viewModel.deleteTask(task.id)
    }

    private fun clickItemListener(task: Task) {
        Log.e("TASK", task.toString())
        findNavController().navigate(R.id.action_homeFragment_to_detailTaskFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel.screenState.observe(viewLifecycleOwner){
            render(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerContainer.isVisible = false
        binding.taskRv.layoutManager = LinearLayoutManager(requireContext())
        binding.taskRv.adapter = adapter

        initListener()
        viewModel.getTasks()
    }

    private fun initListener() {
        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.createTaskFragment)
        }
    }

    private fun render(it: HomeState) {
        when(it){
            is HomeState.Error -> {
                binding.progressCircular.isVisible = false
            }
            HomeState.Loading -> {
                binding.progressCircular.isVisible = true

            }
            is HomeState.Success -> {
                binding.progressCircular.isVisible = false
                if (it.data.isNotEmpty()) {
                    binding.recyclerContainer.isVisible = true
                    binding.emptyTv.isVisible = false
                }else{
                    binding.recyclerContainer.isVisible = false
                    binding.emptyTv.isVisible = true
                }
                adapter.submitList(it.data)
            }
        }
    }
}