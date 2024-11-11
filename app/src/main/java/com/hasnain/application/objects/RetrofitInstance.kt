package com.hasnain.application.objects

import com.hasnain.application.Interfaces.NewsApiService
import com.hasnain.application.models.Article
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitInstance {
    private const val BASE_URL = "https://your-api-base-url.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: NewsApiService = retrofit.create(NewsApiService::class.java)
}
interface ApiService {
    @GET("news") // Adjust endpoint and method as per your API
    suspend fun getNews(): List<Article> // Assuming NewsArticle is your data model
}