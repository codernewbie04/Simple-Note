package com.akmalmf.simplenote.ui.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Akmal Muhamad Firdaus on 2024/08/15 18:19
 * akmalmf007@gmail.com
 */
abstract class BaseRecyclerViewAdapter<VH : RecyclerView.ViewHolder, T> :
    RecyclerView.Adapter<VH>() {

    protected var items: MutableList<T> = mutableListOf()

    var onItemClick: ((T) -> Unit)? = null

    abstract fun onBindViewHolder(holder: VH, item: T, position: Int)


    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, this.items[position], position)
    }

    override fun getItemViewType(position: Int): Int = position

    override fun getItemId(position: Int): Long = position.toLong()

    fun getItem(position: Int): T? = items[position]

    private fun addItem(item: MutableList<T>) {
        clear()
        this.items.addAll(item)
        this.notifyDataSetChanged()
    }

    fun addPosition(position: Int, item: T) {
        this.items.add(position, item)
        notifyItemInserted(position)
    }

    private fun clear() {
        this.items.clear()
    }

    fun setItem(item: MutableList<T>) {
        clear()
        addItem(item)
    }

    fun getItem(): MutableList<T> {
        return items
    }
}