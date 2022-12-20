package com.rajatkumar.navicodinginterview.common.domain

import com.rajatkumar.navicodinginterview.R

sealed class AppError(
    val message: Int = R.string.something_went_wrong,
    val icon: Int = R.drawable.ic_error,
    val cta: Cta = Cta(
        title = R.string.retry,
        type = CtaType.Retry
    ),
    val networkMessage: String? = null,
    val code: String? = null
) {
    class DataNotFound(message: Int = R.string.data_not_found): AppError(message = message)
    class NoInternet(message: Int = R.string.check_connection, icon: Int = R.drawable.ic_no_connection): AppError(message = message, icon = icon)
    class NetworkError(message: String, code: String): AppError(networkMessage = message, code = code)
    class UnknownError(message: String): AppError(networkMessage = message)
}