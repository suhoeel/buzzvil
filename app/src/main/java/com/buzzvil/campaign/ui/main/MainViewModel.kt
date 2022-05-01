package com.buzzvil.campaign.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.buzzvil.campaign.data.Result
import com.buzzvil.campaign.domain.model.CampaignEntity
import com.buzzvil.campaign.domain.GetCampaignsUseCase
import com.buzzvil.campaign.ui.base.BaseViewModel
import com.buzzvil.campaign.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCampaignsUseCase: GetCampaignsUseCase
//    private val campaignsRepository: CampaignsRepository
) : BaseViewModel() {

    private val _campaignEntityList: SingleLiveEvent<Result<List<CampaignEntity>>> = SingleLiveEvent()
    val campaignEntityList: LiveData<Result<List<CampaignEntity>>> = _campaignEntityList

    private val handler = CoroutineExceptionHandler { _, exception ->
        run {
//            toast.value = exception.message
            exception.printStackTrace()
            Log.e("MainViewModel", "Caught $exception")
        }
    }

    init {
        _campaignEntityList.value = Result.Loading
        getConfig()

    }

    fun getConfig() = viewModelScope.launch(handler) {
        _campaignEntityList.value = getCampaignsUseCase()!!
        /*when (val res = campaignsRepository.getConfig()) {
            is Result.Success -> {

            }
            is Result.Error -> {
                Log.d("TEST", "getAds error : ${res.exception.message}")
            }
        }*/
    }

    fun getAds() = viewModelScope.launch {
        /*when (val res = campaignsRepository.getAds()) {
            is Result.Success -> {
                Log.d("TEST", "ads : ${res.data}")
            }
            is Result.Error -> {
                Log.d("TEST", "getAds error : ${res.exception.message}")
            }
        }*/
    }

}