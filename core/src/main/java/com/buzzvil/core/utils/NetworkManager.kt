package com.buzzvil.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkRequest: NetworkRequest
) : ConnectivityManager.NetworkCallback() {

    var hasInternet = false

    private var onAvailable: (() -> Unit)? = null
    private var onLost: (() -> Unit)? = null
    private var onUnAvailable: (() -> Unit)? = null

    fun register(onAvailableCallback: () -> Unit, onLost: () -> Unit, onUnAvailableCallback: () -> Unit) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.isDefaultNetworkActive
        connectivityManager.registerNetworkCallback(networkRequest, this)
        this.onAvailable = onAvailableCallback
        this.onLost = onLost
        this.onUnAvailable = onUnAvailableCallback
    }

    fun unregister() {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(this)
        onAvailable = null
        onLost = null
        this.onUnAvailable = null
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        hasInternet = true
        onAvailable?.invoke()
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        hasInternet = false
        onLost?.invoke()
    }

    override fun onUnavailable() {
        super.onUnavailable()
        hasInternet = false
        onUnAvailable?.invoke()

    }
}
