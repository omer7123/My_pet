package com.example.mypet.ui.petsFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.mypet.domain.entity.PetEntity
import com.example.mypet.domain.entity.TaskEntity

class PetDiffCallback: DiffUtil.ItemCallback<PetEntity>() {
    override fun areItemsTheSame(oldItem: PetEntity, newItem: PetEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PetEntity, newItem: PetEntity): Boolean {
        return oldItem == newItem
    }

}
