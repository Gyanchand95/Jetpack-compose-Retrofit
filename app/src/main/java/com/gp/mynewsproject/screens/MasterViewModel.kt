package com.gp.mynewsproject.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gp.mynewsproject.data.DataOrException
import com.gp.mynewsproject.model.NewsModel
import com.gp.mynewsproject.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MasterViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
     val data: MutableState<DataOrException<NewsModel, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

     suspend fun getNews(category:String):DataOrException<NewsModel,Boolean,Exception>{
         return repository.getNews(category)
     }
}