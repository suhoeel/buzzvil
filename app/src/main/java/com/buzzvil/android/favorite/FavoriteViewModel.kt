package com.buzzvil.android.favorite

import android.util.Log
import androidx.lifecycle.*
import com.buzzvil.commons.ui.base.BaseViewModel
import com.buzzvil.commons.ui.livedata.SingleLiveData
import com.buzzvil.core.database.CampaignEntity
import com.buzzvil.core.repository.CampaignsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val campaignsRepository: CampaignsRepository
) : BaseViewModel() {

    private val _exceptionEvent: SingleLiveData<Throwable> = SingleLiveData()
    val exceptionEvent: LiveData<Throwable>
        get() = _exceptionEvent

    private val _favoriteList: SingleLiveData<List<CampaignEntity>> = SingleLiveData()
    val favoriteList: LiveData<List<CampaignEntity>> = _favoriteList

    private val handler = CoroutineExceptionHandler { _, exception ->
        run {
//            toast.value = exception.message
            _exceptionEvent.value = exception
            Log.e("MainViewModel", "Caught $exception")
        }
    }

    init {
    }

    fun getFavorites() = viewModelScope.launch {
        _favoriteList.value = campaignsRepository.getFavorites()
    }


}