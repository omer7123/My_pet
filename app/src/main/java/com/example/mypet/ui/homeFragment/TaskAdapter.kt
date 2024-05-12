package com.example.mypet.ui.homeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypet.databinding.ItemTaskBinding
import com.example.mypet.domain.entity.TaskEntity

class TaskAdapter(
    private val onItemClickListener: (task: TaskEntity) -> Unit
) :
    ListAdapter<TaskEntity, TaskAdapter.ViewHolder>(TaskDiffCallback()) {
    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: TaskEntity?) = with(binding) {
            titleTv.text = item?.title
            madeBtn.setOnClickListener {
                onItemClickListener(item!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}