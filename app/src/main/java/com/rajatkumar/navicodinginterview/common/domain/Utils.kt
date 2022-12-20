package com.rajatkumar.navicodinginterview.common.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities.*
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE
import android.text.format.DateFormat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


internal suspend fun <T : Any> makeSafeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
    val response: Response<T>
    try {
        response = apiCall.invoke()
    } catch (t: Throwable) {
        t.printStackTrace()
        return Resource.Error(AppError.UnknownError(t.message ?: "Something went wrong!"))
    }
    return if (!response.isSuccessful) {
        Resource.Error(AppError.NetworkError(message = "Something went wrong!", code = "${response.code()}"))
    } else {
        return if (response.body() == null) {
            Resource.Error(AppError.DataNotFound())
        } else {
            Resource.Success(response.body()!!)
        }
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.activeNetworkInfo?.run {
            return when (type) {
                TYPE_WIFI -> true
                TYPE_MOBILE -> true
                TYPE_ETHERNET -> true
                else -> false
            }
        }
    }
    return false
}

fun NavController.navigateSafely(directions: NavDirections, extras: FragmentNavigator.Extras) {
    currentDestination?.getAction(directions.actionId)
        ?.let { navigate(directions, extras) }
}

internal fun String.toDateString(): String {
    return try {
        val format: java.text.DateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).also {
                it.timeZone = TimeZone.getTimeZone("UTC")
            }
        val date = format.parse(this)
        DateFormat.format("d MMM yyyy", date).toString()
    } catch (t: Throwable) {
        ""
    }

}
