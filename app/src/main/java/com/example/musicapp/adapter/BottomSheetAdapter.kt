package com.example.musicapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.ItemListBottomSheetDialogBinding

class BottomSheetAdapter() :
    RecyclerView.Adapter<BottomSheetAdapter.BottomSheetViewHolder>() {
    var data: ArrayList<String> = arrayListOf()
    var listener: ((view: View, item: String, position: Int) -> Unit)? = null
    var checkedPosition: Int = 0

    inner class BottomSheetViewHolder(var binding: ItemListBottomSheetDialogBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
        return BottomSheetViewHolder(
            ItemListBottomSheetDialogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        holder.binding.apply {
            itemSelect = data[position]

            musicChecked = if (checkedPosition == -1) {
                false
            } else {
                checkedPosition == holder.adapterPosition
            }
            clDialogItem.setOnClickListener {
                musicChecked = true
                if (checkedPosition != holder.adapterPosition) {
                    notifyItemChanged(checkedPosition)
                    checkedPosition = holder.adapterPosition
                }
                listener?.invoke(holder.itemView, data[position], position)
            }
        }
    }

    override fun getItemCount() = data.size
    fun addItems(data: ArrayList<String>) {
        data.addAll(data)
        notifyDataSetChanged()
    }


}