package com.example.chucknorrisjokes.ui.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokes.data.remote.service.ChuckService
import com.example.chucknorrisjokes.data.repository.JokeRepository
import com.example.chucknorrisjokes.data.statushandler.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class CategoryViewModel(private val retrofit: Retrofit,
                        private val chuckService: ChuckService,
                        private val jokeRepository: JokeRepository) : ViewModel() {

    private val dataLoading = MutableLiveData<Boolean>()
    val dataLoadingLiveData: LiveData<Boolean> = dataLoading

    private val category = MutableLiveData<List<String>>()
    val categoryLiveData: LiveData<List<String>> = category

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
                }
            }
        }
    }
}