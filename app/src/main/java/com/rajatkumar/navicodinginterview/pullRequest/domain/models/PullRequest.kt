package com.rajatkumar.navicodinginterview.pullRequest.domain.models


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PullRequest(
    @Json(name = "body")
    val body: String?,
    @Json(name = "closed_at")
    val closedAt: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "user")
    val user: User?
): Parcelable
