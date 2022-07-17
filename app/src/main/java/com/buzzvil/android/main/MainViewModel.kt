package com.buzzvil.android.main

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
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val campaignsRepository: CampaignsRepository,
    private val getCampaignsUseCase: GetCampaignsUseCase
) : BaseViewModel() {

    private val _exceptionEvent: SingleLiveData<Throwable> = SingleLiveData()
    val exceptionEvent: LiveData<Throwable>
        get() = _exceptionEvent

    private val _config: SingleLiveData<String> = SingleLiveData()
    val config: LiveData<String> = _config

    private val _campaignEntityList: SingleLiveData<Result<List<CampaignEntity>>> = SingleLiveData()
    val campaignEntityList: LiveData<Result<List<CampaignEntity>>> = _campaignEntityList

    private val handler = CoroutineExceptionHandler { _, exception ->
        run {
//            toast.value = exception.message
            _exceptionEvent.value = exception
            Log.e("MainViewModel", "Caught $exception")
        }
    }

    fun init() = viewModelScope.launch {
        _campaignEntityList.value = Result.Success(campaignsRepository.getCampaigns())
        getConfig()
        Log.d("MainViewModel", "init")
    }

    fun getConfig() = viewModelScope.launch(handler) {
        val result = campaignsRepository.getConfig()
        if(result is Result.Error) _exceptionEvent.value = result.exception

        (result as Result.Success).data.let {
            savedStateHandle["config"] = it
            _config.value = it
            getCampaigns(it, false)
        }

    }

    fun updateCampaign(campaign: CampaignEntity) = viewModelScope.launch(handler) {
        campaignsRepository.updateCampaign(campaign)
    }

    fun getCampaigns(config: String, local: Boolean) = viewModelScope.launch(handler) {
        _campaignEntityList.value = Result.Loading
        val result = getCampaignsUseCase(config, local)
        if(result is Result.Error) _exceptionEvent.value = result.exception

        _campaignEntityList.value = (result as Result.Success)
    }

    fun getCampaignsSaved() = viewModelScope.launch(handler) {
        _campaignEntityList.value = Result.Success(campaignsRepository.getCampaigns())
    }



}