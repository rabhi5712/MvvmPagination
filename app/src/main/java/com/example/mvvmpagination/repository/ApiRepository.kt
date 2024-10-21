package com.example.mvvmpagination.repository

import com.example.mvvmpagination.interfaces.ApiInterface


class ApiRepository(private val apiInterface: ApiInterface) {
    suspend fun getPosts(page : Int) = apiInterface.GET_POSTS(page,20)
}