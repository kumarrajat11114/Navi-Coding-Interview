package com.rajatkumar.navicodinginterview.common.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rajatkumar.navicodinginterview.common.domain.CtaType
import com.rajatkumar.navicodinginterview.common.domain.AppError
import com.rajatkumar.navicodinginterview.common.domain.Event
import com.rajatkumar.navicodinginterview.common.presentation.ErrorEvents.RetryClicked
import com.rajatkumar.navicodinginterview.databinding.LayoutErrorViewBinding
import java.lang.IllegalStateException

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val binding = LayoutErrorViewBinding.inflate(LayoutInflater.from(context), this, true)

    private val errorEventsLiveData = MutableLiveData<Event<ErrorEvents>>()

    fun subscribeEvents(): LiveData<Event<ErrorEvents>> = errorEventsLiveData

    fun show(error: AppError) {
        if (!errorEventsLiveData.hasObservers())
            throw IllegalStateException("Please first subscribe to the Error Events by calling subscribeEvents() method")

        binding.apply {
            errorIcon.setImageDrawable(ContextCompat.getDrawable(context, error.icon))
            errorTitle.text = error.networkMessage ?: context.getString(error.message)
            errorCta.text = context.getString(error.cta.title)
            errorCta.setOnClickListener {
                when (error.cta.type) {
                    CtaType.Retry -> errorEventsLiveData.value = Event(RetryClicked)
                }
            }
        }
    }
}

sealed class ErrorEvents {
    object RetryClicked: ErrorEvents()
}