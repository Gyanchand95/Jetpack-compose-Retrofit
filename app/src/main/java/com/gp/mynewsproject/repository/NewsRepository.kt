package com.gp.mynewsproject.repository

import com.gp.mynewsproject.data.DataOrException
import com.gp.mynewsproject.model.Data
import com.gp.mynewsproject.model.NewsModel
import com.gp.mynewsproject.network.NewsApi
import java.lang.Exception
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: NewsApi) {
    suspend fun getNews(category:String) : DataOrException<NewsModel,Boolean,Exception>{
        val response = try {
            api.getNews(category)
        }catch (e:Exception){
            return DataOrException(e = e)

        }

        return DataOrException(data = response)
    }

}