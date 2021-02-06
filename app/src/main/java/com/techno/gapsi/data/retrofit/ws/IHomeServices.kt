package com.techno.gapsi.data.retrofit.ws

import com.techno.gapsi.data.model.ServiceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface IHomeServices {


    @Headers("X-IBM-Client-Id:adb8204d-d574-4394-8c1a-53226a40876e")
    @GET("search")
    fun getResponseService(
        @Query(value = "query" , encoded = false)
        request: String): Call<ServiceResponse>



}