package com.buzzvil.android.info

import android.util.Log
import androidx.lifecycle.*
import com.buzzvil.commons.ui.base.BaseViewModel
import com.buzzvil.core.network.Result
import com.buzzvil.core.repository.GetCampaignsUseCase
import com.buzzvil.commons.ui.livedata.SingleLiveData
import com.buzzvil.core.database.CampaignEntity
import com.buzzvil.core.repository.CampaignsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val campaignsRepository: CampaignsRepository,
    private val getCampaignsUseCase: GetCampaignsUseCase
) : BaseViewModel() {

    private val _updated: SingleLiveData<Void> = SingleLiveData()
    val updated: LiveData<Void>
        get() = _updated

    private val _exceptionEvent: SingleLiveData<Throwable> = SingleLiveData()
    val exceptionEvent: LiveData<Throwable>
        get() = _exceptionEvent

    private val handler = CoroutineExceptionHandler { _, exception ->
        run {
//            toast.value = exception.message
            _exceptionEvent.value = exception
            Log.e("InfoViewModel", "Caught $exception")
        }
    }


    fun updateCampaign(campaign: CampaignEntity) = viewModelScope.launch(handler) {
        campaignsRepository.updateCampaign(campaign)
        _updated.call()
    }


}