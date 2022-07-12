package com.buzzvil.campaign.domain

import android.util.Log
import com.buzzvil.campaign.data.Result
import com.buzzvil.campaign.data.source.CampaignsRepository
import com.buzzvil.campaign.data.succeeded
import com.buzzvil.campaign.di.IODispatcher
import com.buzzvil.campaign.domain.model.CampaignEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.random.Random

class GetCampaignsUseCase constructor(
    private val campaignsRepository: CampaignsRepository
) {

    //    ratio: String, ads: List<Campaign>, articles: List<Campaign>
    suspend operator fun invoke(): Result<List<CampaignEntity>> {

        /*val ratio = withContext(ioDispatcher) {
            (campaignsRepository.getConfig() as Result.Success).data
        }*/

        val comparator1: Comparator<CampaignEntity> = compareBy { it.firstDisplayPriority }
        val comparator2: Comparator<CampaignEntity> = compareByDescending { it.firstDisplayWeight }

        val ads = campaignsRepository.getAds().let {
            if (it.succeeded) {
                return@let (it as Result.Success).data.sortedWith(comparator1)
            }
            emptyList()
        }

        /*val articles = withContext(ioDispatcher) {
            (campaignsRepository.getArticles() as Result.Success).data
        }.sortedWith(comparator)

        Log.d("TEST", "ads ${ratio.substring(0 until ratio.indexOf(':'))}")
        Log.d("TEST", "articles ${ratio.substring((ratio.indexOf(':') + 1)..ratio.lastIndex)}")
        Log.d("TEST", "ads ${ads}")
        Log.d("TEST", "articles ${articles}")*/

        return Result.Success(ads)
    }

    fun randomAccess() {
        val catalogue: ArrayList<Any> = arrayListOf()
        val randomGenerator = Random(catalogue.size)

        val index = randomGenerator.nextInt()
        val item = catalogue[index]
    }
}