package com.rajatkumar.navicodinginterview.pullRequest.data

import com.rajatkumar.navicodinginterview.pullRequest.data.network.GithubAPI
import com.rajatkumar.navicodinginterview.pullRequest.domain.PullRepository
import com.rajatkumar.navicodinginterview.common.domain.Resource
import com.rajatkumar.navicodinginterview.common.domain.makeSafeApiCall
import com.rajatkumar.navicodinginterview.pullRequest.domain.models.AllPullRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PullRepositoryImpl @Inject constructor(
    private val githubAPI: GithubAPI
): PullRepository {

    override suspend fun getAllPullRequests(): Resource<AllPullRequests> = withContext(Dispatchers.IO) {

        makeSafeApiCall {
            githubAPI.getPullRequest()
        }
    }
}
