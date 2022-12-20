package com.rajatkumar.navicodinginterview.pullRequest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.rajatkumar.navicodinginterview.MainCoroutineRule
import com.rajatkumar.navicodinginterview.data.FakePullRepository
import com.rajatkumar.navicodinginterview.data.TestConditions
import com.rajatkumar.navicodinginterview.common.domain.Resource
import com.rajatkumar.navicodinginterview.getOrAwaitValueTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PullRequestViewModelTest {

    private lateinit var viewModel: PullRequestViewModel
    private lateinit var repo: FakePullRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repo = FakePullRepository()
        viewModel = PullRequestViewModel(repo)
    }

    @Test
    fun `get all pull requests from repo`() {
        viewModel.fireNetworkCall()

        val value = viewModel.allPullRequests.getOrAwaitValueTest()

        print(value)
        assertThat(value).isNotNull()
    }

    @Test
    fun `throw error when list is empty`() {
        repo.testConditions = TestConditions.EMPTY_LIST
        viewModel.fireNetworkCall()

        val value = viewModel.allPullRequests.getOrAwaitValueTest().peekContent()

        print(value)

        assertThat(value).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `throw error when request is bad`() {
        repo.testConditions = TestConditions.BAD_REQUEST
        viewModel.fireNetworkCall()

        val value = viewModel.allPullRequests.getOrAwaitValueTest().peekContent()

        print(value)

        assertThat(value).isInstanceOf(Resource.Error::class.java)
    }

}
