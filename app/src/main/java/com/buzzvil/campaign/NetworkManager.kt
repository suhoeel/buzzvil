package com.buzzvil.campaign

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

    private var onAvailableCallback: (() -> Unit)? = null
    private var onLost: (() -> Unit)? = null


    fun register(onAvailableCallback: () -> Unit, onLost: () -> Unit) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.isDefaultNetworkActive
        connectivityManager.registerNetworkCallback(networkRequest, this)
        this.onAvailableCallback = onAvailableCallback
        this.onLost = onLost
    }

    fun unregister() {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(this)
        onAvailableCallback = null
        onLost = null
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        onAvailableCallback?.invoke()
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        onLost?.invoke()
    }

    override fun onUnavailable() {
        super.onUnavailable()
        Log.d("TEST", "INTERNET onUnavailable?????")

    }
}
