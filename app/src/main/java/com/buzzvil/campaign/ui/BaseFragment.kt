package com.buzzvil.campaign.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.buzzvil.campaign.ui.base.BaseViewModel
import okhttp3.ResponseBody

abstract class BaseFragment<V : BaseViewModel, B : ViewBinding> : Fragment() {
    protected abstract val viewModel: V

    private var _binding: B? = null
    val binding: B get() = _binding!!

    open val apiErrorObserver: Observer<ResponseBody> = Observer { errorResponse ->

    }
    open val networkErrorObserver: Observer<Exception> = Observer {
        /*MaterialDialog(requireContext())
            .cornerRadius(15f)
            .message(text = "서버와 연결이 끊겼습니다. 인터넷 연결을 확인하여주세요.")
            .cancelOnTouchOutside(true)
            .positiveButton {  }
//            .negativeButton {  }
            .show()*/
    }
    open val unknownErrorObserver: Observer<Throwable?> = Observer {
        /*MaterialDialog(requireContext())
            .cornerRadius(15f)
            .message(text = "예상치 못한 서버 에러가 발생하였습니다.")
            .cancelOnTouchOutside(true)
            .positiveButton {  }
            .show()*/
    }

    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): B

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apiError.observe(viewLifecycleOwner, apiErrorObserver)
        viewModel.networkError.observe(viewLifecycleOwner, networkErrorObserver)
        viewModel.unknownError.observe(viewLifecycleOwner, unknownErrorObserver)
        viewModel.stateStart.observe(viewLifecycleOwner) { onProgressStart() }
        viewModel.stateSuccess.observe(viewLifecycleOwner) { onSuccess() }
        viewModel.stateFailure.observe(viewLifecycleOwner) { onFailure() }
        viewModel.stateComplete.observe(viewLifecycleOwner) { onComplete() }

        init()
        observe()
        viewModel.init()
    }

    open fun init() {}
    abstract fun observe()

    open fun onProgressStart() {}
    open fun onComplete() {}
    open fun onSuccess() {}
    open fun onFailure() {}
}