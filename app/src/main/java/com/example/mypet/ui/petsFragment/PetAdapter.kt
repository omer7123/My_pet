package com.example.mypet.ui.petsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypet.databinding.ItemPetBinding
import com.example.mypet.domain.entity.PetEntity

class PetAdapter(
    private val onItemClickListener: (pet: PetEntity) -> Unit
) :
    ListAdapter<PetEntity, PetAdapter.ViewHolder>(PetDiffCallback()) {
    inner class ViewHolder(private val binding: ItemPetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PetEntity) = with(binding) {
            titleTv.text = item.name

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