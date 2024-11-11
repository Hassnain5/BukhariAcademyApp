//package com.hasnain.application.repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.hasnain.application.models.Article // Adjust this to match your actual article model
//import com.hasnain.application.Interfaces.NewsApiService // Adjust package name as per your structure
//import com.hasnain.application.objects.RetrofitInstance.apiService
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class NewsRepository(private val newsApiService: NewsApiService) {
////    suspend fun getTopHeadlines(apiKey: String, country: String): Response<NewsResponse> {
////        return newsApiService.getTopHeadlines(apiKey, country)
////    }
//
//    private val _newsArticles = MutableLiveData<List<Article>>()
//    val newsArticles: LiveData<List<Article>> = _newsArticles
//
//    suspend fun fetchNews() {
//        try {
//            val response = withContext(Dispatchers.IO) {
//                apiService.getNews()
//            }
//            _newsArticles.postValue(response.articles) // Assuming 'articles' is a field in your NewsResponse
//        } catch (e: Exception) {
//            // Handle error
//            throw e // Rethrow the exception or handle it as per your app's error handling strategy
//        }
//    }
//}
