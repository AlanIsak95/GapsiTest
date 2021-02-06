package com.techno.gapsi.ui.home

import com.techno.gapsi.data.model.Item
import com.techno.gapsi.data.model.ServiceResponse
import com.techno.gapsi.data.retrofit.RetrofitRepository
import com.techno.gapsi.utils.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter: BasePresenter<HomePresenter.View>() {

    private var serviceManager : RetrofitRepository = RetrofitRepository()

    /**
     * Interfaces implementadas en HomeActivity
     */
    interface View : BasePresenter.View {
        fun setProgressVisible(boolean : Boolean)
        fun showData(list: List<Item>)
    }


    fun executeHomeService(queryData:String) {



        serviceManager.getHomeServices()?.getResponseService(queryData)?.enqueue(object : Callback<ServiceResponse> {

            override fun onResponse(call: Call<ServiceResponse>, response: Response<ServiceResponse>) {
                response.body()?.items?.let { getView()?.showData(it) }
            }

            override fun onFailure(call: Call<ServiceResponse>, t: Throwable) {

            }


        })




    }






}