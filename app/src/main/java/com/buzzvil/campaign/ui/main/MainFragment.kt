package com.buzzvil.campaign.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.afollestad.materialdialogs.MaterialDialog
import com.buzzvil.campaign.MainActivity
import com.buzzvil.campaign.NetworkManager
import com.buzzvil.campaign.data.Result
import com.buzzvil.campaign.databinding.FragmentMainBinding
import com.buzzvil.campaign.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    companion object {
        private const val TAG = "MainFragment"
    }

    private lateinit var mainSliderAdapter: MainSliderAdapter

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    @Inject
    lateinit var networkManager: NetworkManager

    override val viewModel: MainViewModel by viewModels()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun observe() {
        viewModel.campaignEntityList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    (requireActivity() as MainActivity).setLoadingProgress(true)
                }
                is Result.Success -> {
                    Log.d("TEST", "campaigns ${result.data}")
                    mainSliderAdapter.submitList(result.data)
                    with(requireActivity() as MainActivity) {
                        setLoadingProgress(false)
//                        splashDone()
                    }

                }
                else -> {
                    (requireActivity() as MainActivity).setLoadingProgress(false)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainSliderAdapter = MainSliderAdapter()
        setUpViewPager()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        networkManager.register(onAvailableCallback = {
            Log.d(TAG, "INTERNET onAvailable")
        }, onLost = {
            Log.d(TAG, "INTERNET onLost")
        })

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
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

    override fun onDestroyView() {
        onBackPressedCallback.remove()
        super.onDestroyView()

    }
}