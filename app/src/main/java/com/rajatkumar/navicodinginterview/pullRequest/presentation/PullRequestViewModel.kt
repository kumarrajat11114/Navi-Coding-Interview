package com.rajatkumar.navicodinginterview.pullRequest.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajatkumar.navicodinginterview.R
import com.rajatkumar.navicodinginterview.common.domain.AppError
import com.rajatkumar.navicodinginterview.common.domain.Event
import com.rajatkumar.navicodinginterview.pullRequest.domain.PullRepository
import com.rajatkumar.navicodinginterview.common.domain.Resource
import com.rajatkumar.navicodinginterview.common.domain.isNetworkAvailable
import com.rajatkumar.navicodinginterview.pullRequest.domain.models.AllPullRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val repository: PullRepository
): ViewModel() {

    private val _allPullRequests = MutableLiveData<Event<Resource<AllPullRequests>>>()
    val allPullRequests: LiveData<Event<Resource<AllPullRequests>>>
        get() = _allPullRequests

    fun fireNetworkCall(context: Context) {
        viewModelScope.launch {
            if (!isNetworkAvailable(context)) {
                _allPullRequests.value = Event(Resource.Error(AppError.NoInternet()))
                return@launch
            }
            _allPullRequests.value = Event(Resource.Loading())
            val requests = repository.getAllPullRequests()
            if (requests.data?.pullRequests?.isEmpty() == true) {
                _allPullRequests.value = Event(Resource.Error(AppError.DataNotFound(R.string.pull_request_not_found)))
            } else {
                _allPullRequests.value = Event(requests)
            }
        }
    }

    fun forceRefresh() {
        _allPullRequests.value = _allPullRequests.value
    }

}
