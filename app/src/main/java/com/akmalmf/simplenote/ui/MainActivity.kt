package com.akmalmf.simplenote.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.akmalmf.simplenote.R
import com.akmalmf.simplenote.databinding.ActivityMainBinding
import com.akmalmf.simplenote.ui.adapter.NoteAdapter
import com.akmalmf.simplenote.ui.base.BaseActivity
import com.akmalmf.simplenote.ui.viewmodel.NoteViewModel
import com.akmalmf.simplenote.utils.toGone
import com.akmalmf.simplenote.utils.toVisible

class MainActivity : BaseActivity() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { NoteAdapter() }


    override fun setToolbar(): Toolbar {
        binding.appBar.textToolbar.text = getString(R.string.app_name)

        return binding.appBar.toolbar
    }

    override fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java] // initiate viewmodel

        binding.rvNotes.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedNote = adapter.removeItem(position)
                deletedNote?.let {
                    viewModel.delete(it)
                    snackBarSuccess("Note deleted", "Undo"){
                        adapter.addPosition(position, deletedNote)
                        viewModel.insert(deletedNote)
                    }
                }
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvNotes)

        viewModel.notes.observe(this) { notes ->
            notes?.let {
                if(it.isEmpty()){
                    binding.emptyState.toVisible()
                    binding.rvNotes.toGone()
                } else {
                    binding.emptyState.toGone()
                    binding.rvNotes.toVisible()
                }
                adapter.setItem(it.toMutableList())
            }
        }

        adapter.onItemClick = {
            ManipulateNoteActivity.start(this, it)
        }

        binding.fab.setOnClickListener {
            ManipulateNoteActivity.start(this)
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}