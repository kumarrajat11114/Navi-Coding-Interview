package com.rajatkumar.navicodinginterview.pullRequest.data.network

import com.rajatkumar.navicodinginterview.common.domain.Constants.CREATED
import com.rajatkumar.navicodinginterview.common.domain.Constants.DSC
import com.rajatkumar.navicodinginterview.common.domain.Constants.QUERY
import com.rajatkumar.navicodinginterview.pullRequest.domain.models.AllPullRequests
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubAPI {

    @GET("/search/issues")
    suspend fun getPullRequest(
        @Query("q", encoded = true) query: String = QUERY,
        @Query("sort") sortBy: String = CREATED,
        @Query("order") order: String = DSC
    ): Response<AllPullRequests>

}
