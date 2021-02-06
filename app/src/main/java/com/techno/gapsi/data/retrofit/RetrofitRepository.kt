package com.techno.gapsi.data.retrofit

import com.techno.gapsi.data.retrofit.ws.IHomeServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitRepository {


    fun getHomeServices(): IHomeServices? {
        return getInstance()?.create(IHomeServices::class.java)
    }




    private fun getInstance(): Retrofit? {
        return Retrofit.Builder()
                .baseUrl("https://00672285.us-south.apigw.appdomain.cloud/demo-gapsi/")
                .client(createHttpClient(false))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    private fun createHttpClient(redirect: Boolean): OkHttpClient? {

        val httpClient = OkHttpClient.Builder()
        httpClient.followRedirects(redirect)
        httpClient.connectTimeout(40, TimeUnit.SECONDS)
        httpClient.readTimeout(40, TimeUnit.SECONDS)
        httpClient.writeTimeout(40, TimeUnit.SECONDS)
        return httpClient.build()
    }


}