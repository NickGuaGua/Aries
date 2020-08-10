package com.nickgua.aries

data class AlertButton(val text: String, val function: () -> Unit)

data class AlertItem(
    val title: String? = null, val message: String,
    val positiveButton: AlertButton, val negativeButton: AlertButton? = null
)