package com.nickgua.aries.arch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nickgua.aries.AlertItem
import com.nickgua.aries.Duration
import com.nickgua.aries.R
import com.nickgua.aries.alert
import com.nickgua.aries.arch.common.*
import kotlin.Error

abstract class AriesFragment: Fragment() {

    companion object {
        private val TAG = AriesFragment::class.java.simpleName
    }
    
    abstract val contentViewId: Int

    abstract val loadingViewId: Int

    open val viewModel: AriesAndroidViewModel? = null

    open fun onStateSuccess(state: Success<*>) = Unit

    open fun onStateError(state: Error) = Unit

    open fun onStateLoading() {
        showLoadingView()
    }

    open fun onCustomStateReceived(state: AriesState) = Unit

    open fun onCustomEventReceived(event: AriesEvent) = Unit

    open val baseStateObserver = Observer<AriesState> { state ->
        state ?: return@Observer
        when (state) {
            is Loading -> {
                onStateLoading()
            }
            is Success<*> -> {
                hideLoadingView()
                onStateSuccess(state)
            }
            is Error -> {
                hideLoadingView()
                onStateError(state)
            }
            else -> {
                onCustomStateReceived(state)
            }
        }
    }

    open val baseEventObserver = Observer<AriesEvent> { event ->
        event ?: return@Observer
        when (event) {
            is Toast -> toast(event.message, event.duration)
            is Alert -> alert(event.alertItem)
            is Finish -> finishActivity()
            is BackPress -> onBackPress()
            else -> onCustomEventReceived(event)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val containerView = inflater.inflate(R.layout.aries_fragment_base, container, false)

        // Add content view
        val contentView = inflater.inflate(contentViewId, container, false)
        containerView.findViewById<FrameLayout>(R.id.fragment_container).addView(contentView)

        // Add loading view
        val loadingView = inflater.inflate(loadingViewId, container, false)
        containerView.findViewById<FrameLayout>(R.id.loading_view_container).addView(loadingView)

        return containerView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel?.ariesState?.observe(this, baseStateObserver)
        viewModel?.ariesEvent?.observe(this, baseEventObserver)
    }

    private fun showLoadingView() {
        view?.findViewById<View>(loadingViewId)?.visibility = View.VISIBLE
    }

    private fun hideLoadingView() {
        view?.findViewById<View>(loadingViewId)?.visibility = View.GONE
    }

    protected fun toast(message: String, @Duration duration: Int) {
        android.widget.Toast.makeText(requireContext(), message, duration).show()
    }

    protected fun alert(alertItem: AlertItem) {
        activity?.alert(alertItem) ?: run {
            Log.w(TAG, "Activity is null")
        }
    }

    protected fun finishActivity() {
        activity?.finish() ?: run {
            Log.w(TAG, "Activity is null")
        }
    }

    protected fun onBackPress() {
        activity?.onBackPressed() ?: run {
            Log.w(TAG, "Activity is null")
        }
    }
}