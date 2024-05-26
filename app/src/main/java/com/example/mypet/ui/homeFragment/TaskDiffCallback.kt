package com.example.mypet.ui.homeFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.mypet.domain.entity.Task
import com.example.mypet.domain.entity.TaskEntity

class TaskDiffCallback: DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

}
