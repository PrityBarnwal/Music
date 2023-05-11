package com.example.musicapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapp.R
import com.example.musicapp.databinding.ItemListChildItemBinding
import com.example.musicapp.model.Index
import com.example.musicapp.sataticeModelData.MainChildResponseData

class MainChildAdapter(private val data: ArrayList<Index>):
    RecyclerView.Adapter<MainChildAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(var binding: ItemListChildItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(
            ItemListChildItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }
    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.binding.apply {
            childItemList = data[position]
            Glide.with(ivItem).load(ivItem.context.getString(R.string.image_url,data[position].id)).into(ivItem)
        }
    }
    override fun getItemCount() = data.size
    fun addItems(data: ArrayList<Index>) {
        data.addAll(data)
        notifyDataSetChanged()
    }
}