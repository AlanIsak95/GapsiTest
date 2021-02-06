package com.techno.gapsi.data.room.callback


import com.techno.gapsi.data.room.entity.HistorySearch

interface ICallbackListDataResponse {

    fun success(response: List<HistorySearch>){}

}