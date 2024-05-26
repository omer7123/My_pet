package com.example.mypet.ui.homeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypet.databinding.ItemTaskBinding
import com.example.mypet.domain.entity.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskAdapter(
    private val onAcceptItemClickListener: (task: Task) -> Unit,
    private val onItemClickListener: (task: Task) -> Unit
) :
    ListAdapter<Task, TaskAdapter.ViewHolder>(TaskDiffCallback()) {
    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Task) = with(binding) {
            titleTv.text = item.title
            val date = convertLongToDate(item.date)
            petTv.text = date

            madeBtn.setOnClickListener {
                onAcceptItemClickListener(item)
            }

            binding.root.setOnClickListener {
                onItemClickListener(item)
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

    private fun convertLongToDate(time: Long): String {
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return format.format(Date(time))
    }
}