package com.rajatkumar.navicodinginterview.data

import com.rajatkumar.navicodinginterview.pullRequest.domain.models.AllPullRequests
import com.rajatkumar.navicodinginterview.pullRequest.domain.models.PullRequest
import com.rajatkumar.navicodinginterview.pullRequest.domain.models.User

object FakeDataSource {
    val user = User(
        avatarUrl = "https://avatars.githubusercontent.com/u/25471892?v=4",
        id = 1,
        login = "rajatkumar"
    )

    val prs = listOf<PullRequest>(
        PullRequest(
            body = "Dummy Body",
            closedAt = "2021-08-18T20:16:25Z",
            createdAt = "2021-08-18T20:16:25Z",
            id = 1,
            title = "Dummy Title",
            updatedAt = "2021-08-18T20:16:25Z",
            user = user
        ),
        PullRequest(
            body = "Dummy Body",
            closedAt = "2021-08-18T20:16:25Z",
            createdAt = "2021-08-18T20:16:25Z",
            id = 2,
            title = "Dummy Title",
            updatedAt = "2021-08-18T20:16:25Z",
            user = user
        ),
        PullRequest(
            body = "Dummy Body",
            closedAt = "2021-08-18T20:16:25Z",
            createdAt = "2021-08-18T20:16:25Z",
            id = 3,
            title = "Dummy Title",
            updatedAt = "2021-08-18T20:16:25Z",
            user = user
        ),
    )

    val dummyData = AllPullRequests(
        incompleteResults = true,
        totalCount = 3,
        pullRequests = prs
    )

    val dummyEmptyData = AllPullRequests(
        incompleteResults = true,
        totalCount = 0,
        pullRequests = emptyList()
    )
}
