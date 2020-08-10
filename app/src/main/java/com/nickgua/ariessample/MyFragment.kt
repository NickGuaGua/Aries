package com.nickgua.ariessample

import com.nickgua.aries.arch.AriesAndroidViewModel
import com.nickgua.aries.arch.AriesFragment
import com.nickgua.aries.arch.common.AriesEvent
import com.nickgua.aries.arch.common.AriesState
import com.nickgua.aries.arch.common.Success

class MyFragment : AriesFragment() {

    override val viewModel: AriesAndroidViewModel by lazy {
        MyViewModel(MyApplication.appContext)
    }

    override val contentViewId: Int = R.layout.fragment_my
    override val loadingViewId: Int = R.layout.fragment_my

    override fun onStateSuccess(state: Success<*>) {
        super.onStateSuccess(state)

    }

    override fun onStateError(state: Error) {
        super.onStateError(state)
    }

    override fun onCustomStateReceived(state: AriesState) {
        super.onCustomStateReceived(state)
    }

    override fun onCustomEventReceived(event: AriesEvent) {
        super.onCustomEventReceived(event)
    }
}