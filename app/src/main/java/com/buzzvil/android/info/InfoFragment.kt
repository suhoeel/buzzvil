package com.buzzvil.android.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buzzvil.android.MainActivity
import com.buzzvil.android.R
import com.buzzvil.android.databinding.FragmentInfoBinding
import com.buzzvil.commons.ui.base.BaseFragment
import com.buzzvil.core.database.CampaignEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : BaseFragment<InfoViewModel, FragmentInfoBinding>() {

    companion object {
        private const val TAG = "InfoFragment"
    }

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override val viewModel: InfoViewModel by viewModels()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentInfoBinding.inflate(inflater, container, false)

    override fun observe() {
        viewModel.updated.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_main_info_fragment_to_main_fragment)
        }
        viewModel.exceptionEvent.observe(viewLifecycleOwner) { throwable ->
            throwable.printStackTrace()
            Toast.makeText(requireContext(), "오류 ${throwable.message}", Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).setLoadingProgress(false)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btNotShowing.setOnClickListener {
            viewModel.updateCampaign(
                arguments!!.getParcelable<CampaignEntity>("campaign")!!.apply {
                    isShowing = false
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_main_info_fragment_to_main_fragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }


    override fun onDestroyView() {
        onBackPressedCallback.remove()
        super.onDestroyView()

    }
}