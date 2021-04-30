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
class MainViewModel
@Inject
constructor(private val repository: TunesRepository) : BaseViewModel() {

    var entityValue = MutableLiveData("movie")
    var searchValue = MutableLiveData<String>()
    val tunesModel = MutableLiveData<BaseModel<TunesModel>>()

    fun getAllTunesModelByTermAndEntity(term: String, entity: String, pageNumber: Int) =
        viewModelScope.launch {
            getIsLoading().postValue(true)
            repository.getAllTunesModelByTermAndEntityAndLimit(term, entity, pageNumber)
                ?.let { response ->
                    if (response.isSuccessful) {
                        tunesModel.postValue(response.body())
                    }
                    getIsLoading().value = false
                }
        }

    fun getAllTunesModelByTerm(term: String, entity: String) =
        viewModelScope.launch {
            getIsLoading().postValue(true)
            repository.getAllTunesModelByTerm(term, entity)
                ?.let { response ->
                    if (response.isSuccessful) {
                        tunesModel.postValue(response.body())
                    }
                    getIsLoading().value = false
                }
        }
}