package com.example.musicapp.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.databinding.ItemListFilterBinding
import com.example.musicapp.model.Index

class FilterAdapter( position: Int) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    var resultData: ArrayList<Index> = arrayListOf()
    var listener: ((view: View, item: Index, position: Int) -> Unit)? = null
    private var selectedPosition = position + 1

    inner class FilterViewHolder(var binding: ItemListFilterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            ItemListFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.binding.apply {
            filterItem = resultData[position]

            if (showFilterTitles(position).isNullOrEmpty()){
                clFilter.visibility = View.GONE
            }else{
                itemFilter.text = showFilterTitles(position)
            }

            root.setOnClickListener {
                selectedPosition = holder.adapterPosition
                listener?.invoke(it,resultData[position],position)
                notifyDataSetChanged()
            }
            changeSelectedBackground(
                selectedPosition,
                holder.adapterPosition,
                holder.binding.itemFilter,
                holder.binding.clFilter
            )
        }
    }

    override fun getItemCount() = resultData.size

    private fun changeSelectedBackground(
        selectedPosition: Int,
        bindingPosition: Int,
        textView: TextView, constraintLayout: ConstraintLayout
    ) {
        if (selectedPosition == bindingPosition) {
            constraintLayout.setBackgroundResource(R.drawable.bg_selected)
            textView.apply {
                setTextColor(ContextCompat.getColor(context, R.color.black))
                setTypeface(null, Typeface.BOLD)
            }
            listener?.invoke(textView, resultData[bindingPosition], bindingPosition)
        } else {
            constraintLayout.setBackgroundResource(R.drawable.bg_unselected)
            textView.apply {
                setTextColor(ContextCompat.getColor(context, R.color.white))
                setTypeface(null, Typeface.NORMAL)
            }
        }
    }

    fun showFilterTitles(position: Int): String = when(position){
        0 -> "Only Show Owned"
        1 -> "Skill"
        2 -> "Cirriculum"
        3 -> "Style"
        4 -> "Educator"
        5 -> "Series"
        else -> " "
    }
}