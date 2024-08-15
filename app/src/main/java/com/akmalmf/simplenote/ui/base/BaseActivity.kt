package com.akmalmf.simplenote.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.akmalmf.simplenote.R
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Akmal Muhamad Firdaus on 2024/08/15 18:47
 * akmalmf007@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(), IToolbar {
    protected abstract fun setToolbar(): Toolbar

    override fun onStart() {
        super.onStart()
        setSupportActionBar(setToolbar())

        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowTitleEnabled(setToolbarTitle())
            supportActionBar?.setDisplayHomeAsUpEnabled(setToolbarBack())
            supportActionBar?.setDisplayShowHomeEnabled(setToolbarIcon())

            if (setToolbarBack()) {
                setToolbar().setNavigationIcon(R.drawable.ic_left)
            }
        }
    }
    override fun setToolbarBack(): Boolean = false

    override fun setToolbarIcon(): Boolean = false

    override fun setToolbarTitle(): Boolean = false

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }


    fun snackBarSuccess(message: String, actionTitle: String = "Undo", action: (() -> Unit)? = null) {
        if(action != null){
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(ContextCompat.getColor(this, R.color.green_300))
                .setActionTextColor(ContextCompat.getColor(this, R.color.white))
                .setAction(actionTitle) {
                    action.invoke()
                }
                .show()
            return
        } else {
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(ContextCompat.getColor(this, R.color.green_300))
                .show()
        }
    }

    fun snackBarError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.red_400))
            .show()
    }


}