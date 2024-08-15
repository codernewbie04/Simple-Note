package com.akmalmf.simplenote.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.akmalmf.simplenote.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            delay(1500)
            // Pause the coroutine for 1.5 seconds to display the splash screen before moving to the main activity
            MainActivity.start(this@SplashActivity)
            finish()
            // Finish the current activity (make sure the user can't go back to the splash screen)
        }
    }
}