package com.example.mypet.ui.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypet.R
import com.example.mypet.databinding.FragmentHomeBinding
import com.example.mypet.domain.entity.TaskEntity

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val adapter: TaskAdapter = TaskAdapter(this::clickListener)
    private fun clickListener(taskEntity: TaskEntity) {

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
        val list = listOf(
            TaskEntity("0", "Удалить дизайн", false),
            TaskEntity("1", "Удалить дизайн", false),
            TaskEntity("2", "Удалить дизайн", false),
            TaskEntity("3", "Удалить дизайн", false),
            TaskEntity("4", "Удалить дизайн", false),
            TaskEntity("5", "Удалить дизайн", false),
        )
        adapter.submitList(list)
        binding.taskRv.adapter = adapter


    }
}