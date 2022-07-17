package com.buzzvil.android.favorite

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
import com.buzzvil.android.MainActivity
import com.buzzvil.android.R
import com.buzzvil.android.databinding.FragmentMainBinding
import com.buzzvil.commons.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FavoriteViewModel, FragmentMainBinding>() {

    companion object {
        private const val TAG = "FavoriteFragment"
    }

    private lateinit var favoriteSliderAdapter: FavoriteSliderAdapter

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override val viewModel: FavoriteViewModel by viewModels()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun observe() {
        viewModel.exceptionEvent.observe(viewLifecycleOwner) { throwable ->
            throwable.printStackTrace()
            Toast.makeText(requireContext(), "오류 ${throwable.message}", Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).setLoadingProgress(false)
        }

        viewModel.favoriteList.observe(viewLifecycleOwner) { favorite ->
            Log.d(TAG, "favorite $favorite")
            favoriteSliderAdapter.setData(favorite)
        }

        binding.viewPager.currentItem = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteSliderAdapter = FavoriteSliderAdapter(
            emptyList()
        ) {
            findNavController().navigate(
                R.id.action_main_fragment_to_main_info_fragment,
                bundleOf()
            )
        }.apply {
            setHasStableIds(true)
//            notifyDataSetChanged()
        }
        setUpViewPager()
    }

    private fun setUpViewPager() {
        binding.viewPager.let {
            it.adapter = favoriteSliderAdapter
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

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }


    override fun onDestroyView() {
        onBackPressedCallback.remove()
        super.onDestroyView()

    }
}