package com.app.hepsiburada.view.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.app.hepsiburada.R
import com.app.hepsiburada.core.base.BaseFragment
import com.app.hepsiburada.databinding.ViewFragmentDetailBinding
import com.app.hepsiburada.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<ViewFragmentDetailBinding, DetailViewModel>() {

    override fun getContentView(): Int {
        return R.layout.view_fragment_detail
    }

    override fun subScribe(view: View) {
        init()
        arguments?.let {
            refreshLoad(DetailFragmentArgs.fromBundle(it).id)
        }

        baseViewModel.tunesModel.observe(viewLifecycleOwner, Observer {
            it?.let {
                dataBinding.model = it.results[0]
            }
        })
    }

    fun init() {
        dataBinding.imgBackButton.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToMainFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getViewModel(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    private fun refreshLoad(id: Int) {
        baseViewModel.getTunesModelById(id)
    }
}