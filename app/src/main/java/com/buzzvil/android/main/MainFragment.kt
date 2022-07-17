package com.buzzvil.android.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.buzzvil.android.BuzzvilApp
import com.buzzvil.android.MainActivity
import com.buzzvil.android.R
import com.buzzvil.android.databinding.FragmentMainBinding
import com.buzzvil.commons.ui.base.BaseFragment
import com.buzzvil.core.network.Result
import com.buzzvil.core.utils.NetworkManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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
        viewModel.config.observe(viewLifecycleOwner) { result -> (requireActivity().application as BuzzvilApp).init = true }
        viewModel.campaignEntityList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    (requireActivity() as MainActivity).setLoadingProgress(true)
                }
                is Result.Success -> {
                    Log.d(TAG, "campaigns ${result.data}")
                    mainSliderAdapter.setData(result.data)
                    binding.viewPager.currentItem = 0
                    (requireActivity() as MainActivity).setLoadingProgress(false)
                }
                is Result.Error -> (requireActivity() as MainActivity).setLoadingProgress(false)
            }
        }
        viewModel.exceptionEvent.observe(viewLifecycleOwner) { throwable ->
            throwable.printStackTrace()
            Toast.makeText(requireContext(), "오류 ${throwable.message}", Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).setLoadingProgress(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainSliderAdapter = MainSliderAdapter(
            emptyList(),
            { campaign ->
                val bundle = Bundle()
                bundle.putParcelable("campaign", campaign)
                findNavController().navigate(R.id.action_main_fragment_to_main_info_fragment, bundle)
            },
            { campaign ->
                viewModel.updateCampaign(campaign)
                Toast.makeText(
                    requireContext(),
                    "즐겨찾기에 추가되었습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ).apply {
            setHasStableIds(true)
//            notifyDataSetChanged()
        }
        setUpViewPager()

        if (!networkManager.hasInternet) {
            Toast.makeText(
                requireContext(),
                "오프라인 상태입니다.",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            if(!(requireActivity().application as BuzzvilApp).init) {
                viewModel.init()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCampaignsSaved()
        /*viewModel.config.value?.let {
            viewModel.getCampaigns(it, true)
        }*/
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        networkManager.register(onAvailableCallback = {

        }, onLost = {
            Log.d("TEST", "onLost")
            lifecycleScope.launchWhenStarted {
                Toast.makeText(
                    requireContext(),
                    "오프라인 상태입니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, onUnAvailableCallback = {})

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun setUpViewPager() {

        binding.viewPager.let {
            it.adapter = mainSliderAdapter
            //set the orientation of the viewpager using ViewPager2.orientation
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            //select any page you want as your starting page
            val currentPageIndex = 0
            it.currentItem = currentPageIndex

            it.offscreenPageLimit = 2

            // registering for page change callback
            it.registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)

                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        onBackPressedCallback.remove()
        super.onDestroyView()

    }
}