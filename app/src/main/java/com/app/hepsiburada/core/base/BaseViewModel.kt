package com.app.hepsiburada.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    open fun getIsLoading(): MutableLiveData<Boolean> {
        return isLoading
    }
}