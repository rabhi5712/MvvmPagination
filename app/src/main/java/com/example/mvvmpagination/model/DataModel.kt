package com.example.mvvmpagination.model

data class DataModel(
    val posts: MutableList<Posts>

)

data class Posts(
    val id: Int,
    val title: String,
    val body: String
)