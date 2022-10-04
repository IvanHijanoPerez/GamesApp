package com.example.gamesapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.gamesapp.GamesApp

class Utils {
    companion object {
        fun isInternetAvailable(): Boolean {
            (GamesApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET
                    ) ?: false
                } else {
                    (@Suppress("Deprecation")
                    return this.activeNetworkInfo?.isConnected ?: false)
                }
            }
        }
    }
}