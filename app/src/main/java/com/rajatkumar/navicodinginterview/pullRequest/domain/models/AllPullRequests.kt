package com.rajatkumar.navicodinginterview.pullRequest.domain.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllPullRequests(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    @Json(name = "items")
    val pullRequests: List<PullRequest>,
    @Json(name = "total_count")
    val totalCount: Int
)