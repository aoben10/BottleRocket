package com.theobencode.victoroben.bottlerocket.presentation.storeslist

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.theobencode.victoroben.bottlerocket.R
import com.theobencode.victoroben.bottlerocket.databinding.ListItemStoreDetailsBinding
import com.theobencode.victoroben.bottlerocket.presentation.model.StoreItemEntity
import java.util.*

class StoresListAdapter(private val clickListener: StoreItemClickListener) : RecyclerView.Adapter<StoresListAdapter.StoreViewHolder>() {

    private var stores = ArrayList<StoreItemEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = DataBindingUtil.inflate<ListItemStoreDetailsBinding>(LayoutInflater.from(parent.context), R.layout.list_item_store_details, parent, false)
        return StoreViewHolder(binding)
    }

    override fun getItemCount(): Int = stores.size

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.binding.store = stores[position]
        holder.binding.setListener { clickListener.onStoreClick(stores[position]) }
        holder.binding.executePendingBindings()
    }

    fun setStores(newStores: List<StoreItemEntity>) {
        stores.clear()
        stores.addAll(newStores)
        notifyDataSetChanged()
    }

    interface StoreItemClickListener {
        fun onStoreClick(store: StoreItemEntity)
    }

    inner class StoreViewHolder(val binding: ListItemStoreDetailsBinding) : RecyclerView.ViewHolder(binding.root)

}