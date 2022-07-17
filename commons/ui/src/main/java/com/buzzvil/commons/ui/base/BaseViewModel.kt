package com.buzzvil.commons.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    /*val apiError = SingleLiveData<ResponseBody>()
    val networkError = SingleLiveData<Exception>()
    val unknownError = SingleLiveData<Throwable?>()

    val stateStart = SingleLiveData<Void>()
    val stateComplete = SingleLiveData<Void>()
    val stateSuccess = SingleLiveData<Void>()
    val stateFailure = SingleLiveData<Void>()

    protected fun <T : Any> NetworkResponse<T, ResponseBody>.digest(): T? {
        return when (this) {
            is NetworkResponse.ApiError -> {
                *//*apiError.postValue(
                    ErrorResponse(false,
                    this.code.toString(),
                    this.body?.message ?: "")
                )*//*
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
    }*/
}