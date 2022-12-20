package com.rajatkumar.navicodinginterview.data

import com.rajatkumar.navicodinginterview.data.FakeDataSource.dummyData
import com.rajatkumar.navicodinginterview.data.FakeDataSource.dummyEmptyData
import com.rajatkumar.navicodinginterview.pullRequest.domain.PullRepository
import com.rajatkumar.navicodinginterview.common.domain.Resource
import com.rajatkumar.navicodinginterview.pullRequest.domain.models.AllPullRequests

class FakePullRepository : PullRepository {

    var testConditions: TestConditions = TestConditions.DEFAULT

    override suspend fun getAllPullRequests(): Resource<AllPullRequests> {
        return when (testConditions) {
            TestConditions.EMPTY_LIST -> Resource.Success(dummyEmptyData)
            TestConditions.BAD_REQUEST -> Resource.Error("Something went wrong")
            TestConditions.DEFAULT -> Resource.Success(dummyData)
        }
    }
}

enum class TestConditions {
    EMPTY_LIST, BAD_REQUEST, DEFAULT
}