package com.evaluation.network

import com.evaluation.model.retrofit.NewsItem
import io.reactivex.Single
import retrofit2.http.GET

interface RestApi {

    @GET(".")
    fun newsList(): Single<ArrayList<NewsItem>>

}