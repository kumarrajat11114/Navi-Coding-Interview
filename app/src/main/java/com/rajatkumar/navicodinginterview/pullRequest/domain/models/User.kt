package com.rajatkumar.navicodinginterview.pullRequest.domain.models


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val id: Int,
    val login: String,
): Parcelable
