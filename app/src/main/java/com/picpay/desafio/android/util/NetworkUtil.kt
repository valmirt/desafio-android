package com.picpay.desafio.android.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

object NetworkUtil {
    const val CACHE_SIZE = (5 * 1024 * 1024).toLong()
    private const val WEEK = 60 * 60 * 24 * 7
    private const val AGE = 5

    fun defineCache(chain: Interceptor.Chain, context: Context): Response {
        var request = chain.request()
        request = if (isConnected(context)) {
            request.newBuilder().header(
                name = "Cache-Control",
                value = "public, max-age=$AGE"
            ).build()
        } else {
            request.newBuilder().header(
                name = "Cache-Control",
                value = "public, only-if-cached, max-stale=$WEEK"
            ).build()
        }
        return chain.proceed(request)
    }

    private fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(networkCapabilities) ?: return false

            when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else cm.isDefaultNetworkActive
    }
}