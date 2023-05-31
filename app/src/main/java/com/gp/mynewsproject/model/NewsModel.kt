package com.gp.mynewsproject.model

data class NewsModel(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)

data class Data(
    val author: String,
    val content: String,
    val date: String,
    val id: String,
    val imageUrl: String,
    val readMoreUrl: String,
    val time: String,
    val title: String,
    val url: String
)