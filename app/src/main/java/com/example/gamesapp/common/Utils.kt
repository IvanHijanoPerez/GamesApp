package com.example.gamesapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.gamesapp.GamesApp

class Utils {
    companion object {
        fun isInternetAvailable(): Boolean {
            (GamesApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET
                ) ?: false
            }
        }
    }
}