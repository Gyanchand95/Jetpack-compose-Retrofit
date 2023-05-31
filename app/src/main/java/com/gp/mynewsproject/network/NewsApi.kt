package com.gp.mynewsproject.network

import com.gp.mynewsproject.model.NewsModel
import com.gp.mynewsproject.utils.Constants.NEWS_END_POINT
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NewsApi {

    @GET(value = NEWS_END_POINT)
    suspend fun getNews(
        @Query("category") category:String
    ) : NewsModel

}