package com.example.mypet.ui.petsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypet.databinding.ItemPetBinding
import com.example.mypet.domain.entity.PetEntity
import com.example.mypet.domain.entity.PetItem

class PetAdapter(
    private val onItemClickListener: (pet: PetItem) -> Unit
) :
    ListAdapter<PetItem, PetAdapter.ViewHolder>(PetDiffCallback()) {
    inner class ViewHolder(private val binding: ItemPetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PetItem) = with(binding) {
            titleTv.text = item.name
            root.setOnClickListener {
                onItemClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}