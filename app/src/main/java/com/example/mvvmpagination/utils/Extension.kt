package com.example.mvvmpagination.utils

import org.json.JSONObject
import retrofit2.Response

fun Response<String?>?.hitApi(invokeOnCompletion: (JSONObject) -> Unit) {
    try {
        lateinit var jsonObject: JSONObject
        try {
            jsonObject = if (this!!.isSuccessful) {
                JSONObject(this.body().toString())
            } else {
                JSONObject(this.errorBody()?.string()!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            invokeOnCompletion.invoke(jsonObject)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
