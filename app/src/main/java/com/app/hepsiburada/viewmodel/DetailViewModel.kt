package com.app.hepsiburada.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.hepsiburada.core.base.BaseModel
import com.app.hepsiburada.core.base.BaseViewModel
import com.app.hepsiburada.core.repository.TunesRepository
import com.app.hepsiburada.model.TunesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(private val repository: TunesRepository) :
    BaseViewModel() {
    val tunesModel = MutableLiveData<BaseModel<TunesModel>>()

    fun getTunesModelById(id: Int) =
        viewModelScope.launch {
            getIsLoading().postValue(true)
            repository.getTunesModelById(id)
                .let { response ->
                    if (response.isSuccessful) {
                        tunesModel.postValue(response.body())
                    }
                    getIsLoading().value = false
                }
        }
}