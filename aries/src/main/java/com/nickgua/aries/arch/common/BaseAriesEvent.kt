package com.nickgua.aries.arch.common

import com.nickgua.aries.AlertItem
import com.nickgua.aries.Duration

data class Toast(val message: String, @Duration val duration: Int): AriesEvent

data class Alert(val alertItem: AlertItem) : AriesEvent

object Finish: AriesEvent

object BackPress: AriesEvent