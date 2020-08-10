package com.nickgua.aries.arch.common

object Loading: AriesState

data class Success<T> (val response: T): AriesState

data class Error (val exception: Exception): AriesState