package com.example.mvvmpagination.interfaces

import com.example.mvvmpagination.utils.Apis
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET(Apis.GET_POSTS)
    suspend fun GET_POSTS(@Query ("skip") skip : Int,
                         @Query ("limit") limit : Int): Response<String?>?
}