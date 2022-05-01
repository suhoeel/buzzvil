package com.buzzvil.campaign.ui.base

import androidx.lifecycle.ViewModel
import com.buzzvil.campaign.domain.response.base.NetworkResponse
import com.buzzvil.campaign.utils.SingleLiveEvent
import okhttp3.ResponseBody

abstract class BaseViewModel : ViewModel() {
    val apiError = SingleLiveEvent<ResponseBody>()
    val networkError = SingleLiveEvent<Exception>()
    val unknownError = SingleLiveEvent<Throwable?>()

    val stateStart = SingleLiveEvent<Void>()
    val stateComplete = SingleLiveEvent<Void>()
    val stateSuccess = SingleLiveEvent<Void>()
    val stateFailure = SingleLiveEvent<Void>()

    protected fun <T : Any> NetworkResponse<T, ResponseBody>.digest(): T? {
        return when (this) {
            is NetworkResponse.ApiError -> {
                /*apiError.postValue(
                    ErrorResponse(false,
                    this.code.toString(),
                    this.body?.message ?: "")
                )*/
                complete()
                failure()
                null
            }
            is NetworkResponse.NetworkError -> {
                networkError.postValue(this.error)
                complete()
                failure()
                null
            }
            is NetworkResponse.UnknownError -> {
                unknownError.postValue(this.error)
                complete()
                failure()
                null
            }
            is NetworkResponse.Success -> {
                complete()
                this.body
            }
        }
    }

    open fun init() {}

    protected fun start() {
        stateStart.callAsync()
    }

    protected fun complete() {
        stateComplete.callAsync()
    }

    protected fun success() {
        stateSuccess.callAsync()
    }

    protected fun failure() {
        stateFailure.callAsync()
    }
}