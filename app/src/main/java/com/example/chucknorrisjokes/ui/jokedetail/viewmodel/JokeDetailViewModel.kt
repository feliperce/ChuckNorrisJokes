package com.example.chucknorrisjokes.ui.jokedetail.viewmodel

import androidx.lifecycle.*
import com.example.chucknorrisjokes.data.repository.JokeRepository
import com.example.chucknorrisjokes.data.statushandler.Status
import com.example.chucknorrisjokes.exception.ErrorException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class JokeDetailViewModel(private val jokeRepository: JokeRepository) : ViewModel() {

    private val dataLoading = MutableLiveData<Boolean>()
    val dataLoadingLiveData: LiveData<Boolean> = dataLoading

    private val jokeText = MutableLiveData<String>()
    val jokeLiveData: LiveData<String> = jokeText

    private val iconUrl = MutableLiveData<String>()
    val iconLiveData: LiveData<String> = iconUrl

    private val url = MutableLiveData<String>()
    val urlLiveData: LiveData<String> = url

    private val errorHandler = MutableLiveData<ErrorException>()
    val errorHandlerLiveData: LiveData<ErrorException> = errorHandler

    fun getJokeByCategory(category: String) {
        viewModelScope.launch {
            jokeRepository.getRandomJokeByCategory(category).collect { res ->
                when (res.status) {
                    is Status.Success -> {
                        res.data?.let { data ->
                            jokeText.postValue(data.value)
                            iconUrl.postValue(data.iconUrl)
                            url.postValue(data.url)
                        }
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