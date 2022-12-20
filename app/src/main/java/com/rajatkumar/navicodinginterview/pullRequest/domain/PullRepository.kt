package com.rajatkumar.navicodinginterview.pullRequest.domain

import com.rajatkumar.navicodinginterview.common.domain.Resource
import com.rajatkumar.navicodinginterview.pullRequest.domain.models.AllPullRequests

interface PullRepository {
    suspend fun getAllPullRequests(): Resource<AllPullRequests>
}
