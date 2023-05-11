package com.example.musicapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapp.R
import com.example.musicapp.databinding.ItemListFilterMusicBinding
import com.example.musicapp.model.Index

class RecentlyWatchedAdapter :
    RecyclerView.Adapter<RecentlyWatchedAdapter.RecentlyWatchedViewHolder>() {
    var resultData: ArrayList<Index> = arrayListOf()

    inner class RecentlyWatchedViewHolder(var binding: ItemListFilterMusicBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyWatchedViewHolder {
        return RecentlyWatchedViewHolder(
            ItemListFilterMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecentlyWatchedViewHolder, position: Int) {
        holder.binding.apply {
            Log.d("TAG", "onBindViewHolderRes: ${resultData[position]}")
            listMusic = resultData[position]
            Glide.with(ivItem).load(ivItem.context.getString(R.string.image_url,resultData[position].id)).into(ivItem)
        }
    }

    override fun getItemCount() = resultData.size

    fun addItems(data: ArrayList<Index>) {
        data.addAll(data)
        notifyDataSetChanged()
    }
}

