package com.app.hepsiburada.core.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.hepsiburada.R

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> :
    Fragment() {

    protected lateinit var dataBinding: T

    protected lateinit var baseViewModel: V

    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_loading)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        baseViewModel = ViewModelProvider(this).get(getViewModel())

        dataBinding = DataBindingUtil.inflate(inflater, getContentView(), container, false)
        dataBinding.setVariable(getContentView(), baseViewModel)
        dataBinding.executePendingBindings()

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showChangeProgress()
        subScribe(view)
    }

    open fun hideProgressDialog() {
        dialog?.let {
            it.dismiss()
            it.hide()
        }
    }

    open fun showProgressDialog() {
        dialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    open fun showChangeProgress() {
        baseViewModel.getIsLoading().observe(viewLifecycleOwner, Observer {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        })
    }

    fun showMessageBox(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected abstract fun getContentView(): Int

    protected abstract fun subScribe(view: View)

    protected abstract fun getViewModel(): Class<V>
}