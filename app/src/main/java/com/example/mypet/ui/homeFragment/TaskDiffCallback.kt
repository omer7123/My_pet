package com.example.mypet.ui.homeFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.mypet.domain.entity.TaskEntity

class TaskDiffCallback: DiffUtil.ItemCallback<TaskEntity>() {
    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem == newItem
    }

}
