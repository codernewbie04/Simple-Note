package com.akmalmf.simplenote.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akmalmf.simplenote.R
import com.akmalmf.simplenote.data.model.Note
import com.akmalmf.simplenote.databinding.ItemNoteBinding
import com.akmalmf.simplenote.ui.base.BaseRecyclerViewAdapter
import com.akmalmf.simplenote.utils.formatTimestamp

/**
 * Created by Akmal Muhamad Firdaus on 2024/08/15 18:04
 * akmalmf007@gmail.com
 */
class NoteAdapter: BaseRecyclerViewAdapter<NoteAdapter.VHolder, Note>(){
    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)
        fun onBind(model: Note) {
            binding.textTitle.text = model.title
            binding.textDescription.text = model.description
            binding.textTime.text = model.createdAt.formatTimestamp()

            binding.root.setOnClickListener {
                onItemClick?.invoke(model)
            }
        }
    }

    override fun onBindViewHolder(holder: VHolder, item: Note, position: Int) {
        holder.onBind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    fun removeItem(position: Int): Note? {
        return if (position in 0 until items.size) {
            val removedItem = items.removeAt(position)
            notifyItemRemoved(position)
            removedItem
        } else {
            null
        }
    }
}