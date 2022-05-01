package com.buzzvil.campaign.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.buzzvil.campaign.MainActivity
import com.buzzvil.campaign.data.Result
import com.buzzvil.campaign.databinding.FragmentMainBinding
import com.buzzvil.campaign.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    private lateinit var mainSliderAdapter: MainSliderAdapter

    override val viewModel: MainViewModel by viewModels()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun observe() {
        viewModel.campaignEntityList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                    (requireActivity() as MainActivity).setLoadingProgress(true)
                }
                is Result.Success -> {
                    with(requireActivity() as MainActivity) {
                        setLoadingProgress(false)
                        splashDone()
                    }
                    mainSliderAdapter = MainSliderAdapter(result.data)
                    setUpViewPager()
                }
                else -> {
                    (requireActivity() as MainActivity).setLoadingProgress(false)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun setUpViewPager() {

        binding.viewPager.adapter = mainSliderAdapter

        //set the orientation of the viewpager using ViewPager2.orientation
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //select any page you want as your starting page
        val currentPageIndex = 1
        binding.viewPager.currentItem = currentPageIndex

        // registering for page change callback
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    //update the image number textview
//                    binding. .text = "${position + 1} / 4"
                }
            }
        )
    }
}