package com.example.cryptocurrencyapp.presentation.coin_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.databinding.ItemCoinBinding
import com.example.cryptocurrencyapp.domain.model.Coin

class CoinListAdapter(private val itemClickListener: ((noteId: String?) -> Unit)? = null) :
    ListAdapter<Coin, CoinListAdapter.ViewHolder>(CoinDiffCallback()) {

    inner class ViewHolder(private val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {
            binding.item = coin
            itemView.setOnClickListener {
                itemClickListener?.invoke(coin.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class CoinDiffCallback : DiffUtil.ItemCallback<Coin>() {

    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }

}