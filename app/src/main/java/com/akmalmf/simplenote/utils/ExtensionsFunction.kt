package com.akmalmf.simplenote.utils

import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

/**
 * Created by Akmal Muhamad Firdaus on 2024/08/15 17:53
 * akmalmf007@gmail.com
 */


/** Get string from EditText */
fun EditText.getString(): String {
    return this.text.toString()
}

/**
 * Format Long to string dd/MM/yyyy HH:mm (e.g. 15/08/2024 17:53)
 */
fun Long.formatTimestamp(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(this)
}