package com.example.mypet.ui.petsFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.mypet.domain.entity.PetItem

class PetDiffCallback: DiffUtil.ItemCallback<PetItem>() {
    override fun areItemsTheSame(oldItem: PetItem, newItem: PetItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PetItem, newItem: PetItem): Boolean {
        return oldItem == newItem
    }

}
