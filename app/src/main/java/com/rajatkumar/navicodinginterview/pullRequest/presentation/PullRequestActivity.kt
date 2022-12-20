package com.rajatkumar.navicodinginterview.pullRequest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rajatkumar.navicodinginterview.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
    }
}