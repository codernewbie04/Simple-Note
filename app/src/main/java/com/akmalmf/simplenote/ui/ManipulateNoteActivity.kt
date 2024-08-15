package com.akmalmf.simplenote.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.akmalmf.simplenote.R
import com.akmalmf.simplenote.data.model.Note
import com.akmalmf.simplenote.databinding.ActivityManipulateNoteBinding
import com.akmalmf.simplenote.ui.base.BaseActivity
import com.akmalmf.simplenote.ui.viewmodel.NoteViewModel

class ManipulateNoteActivity : BaseActivity() {

    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: ActivityManipulateNoteBinding
    private var note: Note? = null
    private var dirtyText = false
    // dirty text is used to check if user has made any changes to the note, if yes, show confirmation dialog, if no, just finish the activity


    override fun initView() {
        binding = ActivityManipulateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        setupActionBar()

        binding.etTitle.setOnFocusChangeListener { _, _ ->
            dirtyText = true
        }
        binding.etDescription.setOnFocusChangeListener { _, _ ->
            dirtyText = true
        }
    }

    private fun setupActionBar() {
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.blue_200))
        )
    }

    override fun setToolbar(): Toolbar {
        val toolbar = binding.appBar.toolbar
        val isEditing = note != null

        if (isEditing) {
            binding.etTitle.setText(note?.title)
            binding.etDescription.setText(note?.description)
            binding.appBar.textToolbar.text = getString(R.string.edit_note)
        } else {
            binding.appBar.textToolbar.text = getString(R.string.add_note)
        }
        return toolbar
    }

    override fun setToolbarBack(): Boolean {
        binding.appBar.toolbar.setNavigationOnClickListener {
            showExitConfirmationDialog()
        }
        return true
    }

    override fun onBackPressed() {
        //make sure user really want to exit
        showExitConfirmationDialog()
    }


    private fun showExitConfirmationDialog() {
        if (dirtyText) {
            AlertDialog.Builder(this)
                .setTitle(R.string.exit_confirmation_title)
                .setMessage(R.string.exit_confirmation_message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.yes) { _, _ -> finish() }
                .setNegativeButton(R.string.cancel, null)
                .show()
        } else {
            finish()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        binding.btnSave.setOnClickListener { onSaveButtonClicked() }
    }

    private fun onSaveButtonClicked() {
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()

        if (title.isNotEmpty() && description.isNotEmpty()) {
            val noteToSave = note?.copy(title = title, description = description) ?: Note(
                title = title,
                description = description,
                createdAt = System.currentTimeMillis()
            )
            if (note != null) {
                viewModel.update(noteToSave)
            } else {
                viewModel.insert(noteToSave)
            }
            finish()
        } else {
            snackBarError(getString(R.string.error_empty_fields))
        }
    }

    companion object {
        private const val EXTRA_NOTE = "extra_note"

        fun start(context: Context) {
            val intent = Intent(context, ManipulateNoteActivity::class.java)
            context.startActivity(intent)
        }

        fun start(context: Context, note: Note) {
            val intent = Intent(context, ManipulateNoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE, note)
            }
            context.startActivity(intent)
        }
    }
}
