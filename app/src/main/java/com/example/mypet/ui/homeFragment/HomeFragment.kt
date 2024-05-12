package com.example.mypet.ui.homeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypet.R
import com.example.mypet.databinding.FragmentHomeBinding
import com.example.mypet.domain.entity.TaskEntity

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    var list = mutableListOf(
        TaskEntity("0", "Удалить дизайн", false),
        TaskEntity("1", "Удалить дизайн", false),
        TaskEntity("2", "Удалить дизайн", false),
        TaskEntity("3", "Удалить дизайн", false),
        TaskEntity("4", "Удалить дизайн", false),
        TaskEntity("5", "Удалить дизайн", false),
    )
    private val adapter = TaskAdapter(this::clickListener)

    private fun clickListener(taskEntity: TaskEntity) {
        val newList = list.toMutableList()
        newList.remove(taskEntity)
        list = newList
        adapter.submitList(newList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskRv.layoutManager = LinearLayoutManager(requireContext())
        binding.taskRv.adapter = adapter
        adapter.submitList(list)
    }
}