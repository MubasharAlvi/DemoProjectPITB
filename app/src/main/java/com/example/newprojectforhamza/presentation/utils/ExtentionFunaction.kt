package com.example.newprojectforhamza.presentation.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.text.SpannedString
import android.view.View
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.italic
import androidx.core.text.underline

//for Under funcion
fun Activity.formattedUnderLineFun(textForUnderline: String): SpannedString {
    val formattedText = buildSpannedString {
        append(textForUnderline)
        bold { append("Bold") }
        italic { append("Italic") }
        underline { append("Underline") }
        bold { italic { append("Bold Italic") } }
    }
    return formattedText
}

//for NoneUnder funcion
fun Activity.formattedNoneunderLineFun(textForUnderline: String): SpannedString {
    val formattedText = buildSpannedString {
        append(textForUnderline)
        bold { append("Bold") }
        italic { append("Italic") }
        bold { italic { append("Bold Italic") } }
    }
    return formattedText
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun Context.NetWorkCheck():Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

/** Check internet Connection */
/** Returns “WiFi”, “Mobile”, “Ethernet”, “Other”, or “NoConnection”. */
fun Context.connectionType(): String {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = cm.activeNetwork ?: return "NoConnection"
        val nc = cm.getNetworkCapabilities(network) ?: return "NoConnection"
        return when {
            nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)      -> "WiFi"
            nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)  -> "Mobile"
            nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)  -> "Ethernet"
            else                                                    -> "Other"
        }
    }
    return "Unknown"      // API < 23
}

/** True only when the device has a *validated* network → real internet. */
fun Context.isOnline(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw   = cm.activeNetwork       ?: return false
        val caps = cm.getNetworkCapabilities(nw) ?: return false
        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    } else {
        @Suppress("DEPRECATION") return cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }
}

