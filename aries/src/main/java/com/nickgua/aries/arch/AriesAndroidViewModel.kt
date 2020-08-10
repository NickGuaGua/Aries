package com.nickgua.aries.arch

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nickgua.aries.SingleLiveEvent
import com.nickgua.aries.arch.common.AriesEvent
import com.nickgua.aries.arch.common.AriesState

open class AriesAndroidViewModel(application: Application): AndroidViewModel(application) {

    val ariesEvent = SingleLiveEvent<AriesEvent>()

    private val _ariesState = MutableLiveData<AriesState>()
    val ariesState: LiveData<AriesState> = _ariesState

    protected fun postState(state: AriesState) {
        _ariesState.postValue(state)
    }

    protected fun postEvent(event: AriesEvent) {
        ariesEvent.postValue(event)
    }

    protected fun getResString(@StringRes id: Int): String {
        return getApplication<Application>().getString(id)
    }
}