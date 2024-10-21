package com.example.mvvmpagination.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpagination.databinding.ItemLayoutBinding
import com.example.mvvmpagination.model.Posts


class ItemAdapter(private val items: MutableList<Posts>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ItemViewHolder =
        ItemViewHolder(
        ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun addItems(newItems: List<Posts>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Posts) {
            binding.apply {
                title.text = item.title
                body.text = item.body
            }
        }
    }
}
