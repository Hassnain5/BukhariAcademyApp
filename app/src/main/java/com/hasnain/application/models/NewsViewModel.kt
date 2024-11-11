//package com.hasnain.application.viewmodel
//
//import androidx.lifecycle.*
//import com.hasnain.application.models.NewsResponse
//import com.hasnain.application.repository.NewsRepository
//import kotlinx.coroutines.launch
//import retrofit2.Response
//
//class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
//
////private val _newsResponse = MutableLiveData<Response<NewsResponse>>()
////    val newsResponse: LiveData<Response<NewsResponse>> get() = _newsResponse
////
////    fun fetchTopHeadlines(apiKey: String, country: String) {
////        viewModelScope.launch {
////            val response = repository.getTopHeadlines(apiKey, country)
////            _newsResponse.postValue(response)
////        }
////    }
//}
