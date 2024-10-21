package com.example.mvvmpagination.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpagination.repository.ApiRepository
import com.example.mvvmpagination.utils.hitApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    var page = 1
    private val handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    private val dataMutable: MutableLiveData<JSONObject> = MutableLiveData()

    val getLiveData: LiveData<JSONObject>
        get() = dataMutable

    fun getPosts(page : Int) = viewModelScope.launch(handler) {
        repository.getPosts(page).hitApi {
            dataMutable.postValue(it)
        }
    }
}