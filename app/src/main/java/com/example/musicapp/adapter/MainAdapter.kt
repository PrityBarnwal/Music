package com.example.musicapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.ItemListMainBinding
import com.example.musicapp.model.Result

class MainAdapter(var resultData : Result? = null) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var listener: ((view: View, item: Result, position: Int) -> Unit)? =
        null


    inner class MainViewHolder(var binding: ItemListMainBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemListMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val mainChildAdapter: MainChildAdapter by lazy {
            MainChildAdapter(resultData!!.index)
        }

        holder.binding.apply {
            mainItem = resultData!!.collections.smart[position]
            Log.d("resultData", "onBindViewHolder: $resultData")
            mainChildAdapter.addItems(
                resultData!!.index
            )
            recChildItem.adapter = mainChildAdapter

            tvViewAll.setOnClickListener {
                listener?.invoke(holder.itemView, resultData!!, position)
            }
        }
    }

    override fun getItemCount() = resultData?.collections?.smart?.size ?: 0
    fun addItems(data:Result) {
        resultData = data
        notifyDataSetChanged()
    }
}