package com.rajatkumar.navicodinginterview.common.domain

data class Cta(
    val title: Int,
    val type: CtaType
)

sealed class CtaType {
    object Retry: CtaType()
}