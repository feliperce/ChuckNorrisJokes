package com.example.chucknorrisjokes.ui.category.viewmodel

import androidx.lifecycle.*
import com.example.chucknorrisjokes.data.remote.service.ChuckService
import com.example.chucknorrisjokes.data.repository.JokeRepository
import com.example.chucknorrisjokes.data.statushandler.Status
import com.example.chucknorrisjokes.exception.ErrorException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

class CategoryViewModel(private val jokeRepository: JokeRepository) : ViewModel() {

    private val dataLoading = MutableLiveData<Boolean>()
    val dataLoadingLiveData: LiveData<Boolean> = dataLoading

    private val category = MutableLiveData<List<String>>()
    val categoryLiveData: LiveData<List<String>> = category

    private val errorHandler = MutableLiveData<ErrorException>()
    val errorHandlerLiveData: LiveData<ErrorException> = errorHandler

    fun getJokeCategories() {
        viewModelScope.launch {
            jokeRepository.getJokeCategories().collect { res ->
                when (res.status) {
                    is Status.Success -> {
                        category.postValue(res.data)
                    }
                    is Status.Loading -> {
                        dataLoading.postValue(res.status.isLoading)
                    }
                    is Status.Error -> {
                        errorHandler.postValue(res.status.exception)
                    }
                }
            }
        }
    }
}