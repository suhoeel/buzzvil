package com.buzzvil.core.repository

import android.util.Log
import com.buzzvil.core.database.AdEntity
import com.buzzvil.core.database.ArticleEntity
import com.buzzvil.core.database.CampaignEntity
import com.buzzvil.core.di.scope.DefaultDispatcher
import com.buzzvil.core.model.mapper.CampaignMapper
import com.buzzvil.core.network.Result
import com.buzzvil.core.network.succeeded
import com.buzzvil.core.utils.Utils.swap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCampaignsUseCase @Inject constructor(
    private val campaignsRepository: CampaignsRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    companion object {
        private const val TAG = "GetCampaignsUseCase"
    }

    suspend operator fun invoke(config: String, local: Boolean): Result<List<CampaignEntity>> =
        withContext(defaultDispatcher) {

            val ratioOfAds: Int
            val ratioOfArticles: Int

            config.let {
                val c = it.indexOf(':')
                ratioOfAds = it.substring(0 until c).toInt()
                ratioOfArticles = it.substring((c + 1)..it.lastIndex).toInt()

                Log.d(TAG, "ratio ads $ratioOfAds")
                Log.d(TAG, "ratio articles $ratioOfArticles")
            }

            val ads: MutableList<AdEntity>
            val articles: List<ArticleEntity>

            if (local) {
                ads = campaignsRepository.getAds().toMutableList()
                articles = campaignsRepository.getArticles()
            } else {
                val adsResult = campaignsRepository.fetchAds()
                val articlesResult = campaignsRepository.fetchArticles()

                if (!adsResult.succeeded) return@withContext (adsResult as Result.Error)
                if (!articlesResult.succeeded) return@withContext (articlesResult as Result.Error)

                ads = (adsResult as Result.Success).data.toMutableList()
                articles = (articlesResult as Result.Success).data
            }

//        Log.d(TAG, "1 ${ads.chunked(ratioOfAds)}")
//        Log.d(TAG, "2 ${articles.chunked(ratioOfArticles)}")

            // first image
            val groupByAscentPrior = with(ads.sortedBy { it.firstDisplayPriority }) {
                this.filter {
                    (it.firstDisplayPriority == this[0].firstDisplayPriority)
                }
            }
            val n = groupByAscentPrior.size
            val freq = groupByAscentPrior.map { it.firstDisplayWeight }.toIntArray()

            /*for (i in freq) {
                Log.d(TAG, "freq ${i}")
            }*/

            val firstImage = myRand(groupByAscentPrior, freq, n)
            Log.d(TAG, "firstImage ${firstImage}")

            swap(ads, 0, ads.indexOf(firstImage))

            // 비율을 맞추기에 AD의 숫자가 부족한데 AD를 반복해서 사용해도 될까요?
            val campaigns = ArrayList<CampaignEntity>()
            procedureInsert(
                campaigns,
                ads,
                ratioOfAds,
                articles,
                ratioOfArticles
            )
//        Log.d(TAG, "campaigns ${campaigns}")
            saveCampaigns(campaigns)
            return@withContext Result.Success(campaigns)
        }

    private suspend fun saveCampaigns(campaigns: List<CampaignEntity>) {
        campaignsRepository.saveCampaigns(campaigns)
    }

    private fun procedureInsert(
        campaigns: ArrayList<CampaignEntity>,
        ads: List<AdEntity>,
        adRatio: Int,
        article: List<ArticleEntity>,
        articleRatio: Int
    ): List<CampaignEntity> {
        var articleIndex = 0
        var adIndex = 0
        for (i in 1..(ads.size + article.size)) {
            campaigns.add(CampaignMapper.adEntityToCampaign(ads[adIndex]))
            adIndex++
            if (adIndex == ads.size) adIndex = 0
            if (i % adRatio == 0) {
                for (j in 0 until articleRatio) {
                    campaigns.add(CampaignMapper.articleEntityToCampaign(article[articleIndex]))
                    articleIndex++
                    if (articleIndex == article.size) return campaigns
                }
            }
        }
        return campaigns
    }

    private fun findCeil(arr: IntArray, r: Int, l: Int, h: Int): Int {
        var ll = l
        var hh = h
        var mid: Int
        while (ll < hh) {
            mid = ll + (hh - ll shr 1) // Same as mid = (l+h)/2
            if (r > arr[mid]) ll = mid + 1 else hh = mid
        }
        return if (arr[ll] >= r) ll else -1
    }

    private fun myRand(arr: List<AdEntity>, freq: IntArray, n: Int): AdEntity {
        val prefix = IntArray(n)
        prefix[0] = freq[0]
        var i = 1
        while (i < n) {
            prefix[i] = prefix[i - 1] + freq[i]
            ++i
        }
        val r = (Math.random() * 323567).toInt() % prefix[n - 1] + 1
        val indexc = findCeil(prefix, r, 0, n - 1)
        return arr[indexc]
    }
}