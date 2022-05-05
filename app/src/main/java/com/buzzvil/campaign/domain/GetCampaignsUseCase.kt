package com.buzzvil.campaign.domain

import android.util.Log
import com.buzzvil.campaign.data.Result
import com.buzzvil.campaign.data.source.CampaignsRepository
import com.buzzvil.campaign.di.IODispatcher
import com.buzzvil.campaign.domain.model.CampaignEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCampaignsUseCase constructor(
    private val campaignsRepository: CampaignsRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {


    fun getAds() {

    }

    fun getArticles() {

    }

    //    ratio: String, ads: List<Campaign>, articles: List<Campaign>
    suspend operator fun invoke(): Result<List<CampaignEntity>> {

        /*val ratio = withContext(ioDispatcher) {
            (campaignsRepository.getConfig() as Result.Success).data
        }*/

        val comparator : Comparator<CampaignEntity> = compareBy { it.firstDisplayPriority }

        val ads = withContext(ioDispatcher) {
            (campaignsRepository.getAds() as Result.Success).data
        }.sortedWith(comparator)


        /*val articles = withContext(ioDispatcher) {
            (campaignsRepository.getArticles() as Result.Success).data
        }.sortedWith(comparator)

        Log.d("TEST", "ads ${ratio.substring(0 until ratio.indexOf(':'))}")
        Log.d("TEST", "articles ${ratio.substring((ratio.indexOf(':') + 1)..ratio.lastIndex)}")
        Log.d("TEST", "ads ${ads}")
        Log.d("TEST", "articles ${articles}")*/

        return Result.Success(ads.ifEmpty { emptyList() })
    }
}