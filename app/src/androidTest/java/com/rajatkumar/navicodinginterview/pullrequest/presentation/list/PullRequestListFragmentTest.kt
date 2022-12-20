package com.rajatkumar.navicodinginterview.pullrequest.presentation.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.rajatkumar.navicodinginterview.R
import com.rajatkumar.navicodinginterview.pullRequest.presentation.PullRequestActivity
import com.rajatkumar.navicodinginterview.pullRequest.presentation.list.adapters.PRsAdapter
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class PullRequestListFragmentTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(PullRequestActivity::class.java)

    val itemToTest = 8

    fun test_IsPullRequestListFragmentVisible() {
        onView(withId(R.id.rv_prs)).check(matches(isDisplayed()))
    }

    fun test_NavigateToDetailsFragmentOnItemClick() {
        onView(withId(R.id.rv_prs))
            .perform(actionOnItemAtPosition<PRsAdapter.PRViewHolder>(itemToTest, click()))

        onView(withId(R.id.iv_profile)).check(matches(isDisplayed()))

    }


}
