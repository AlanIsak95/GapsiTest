package com.techno.gapsi.data.room

import android.content.Context
import com.techno.gapsi.data.model.Item
import com.techno.gapsi.data.room.callback.ICallbackBase
import com.techno.gapsi.data.room.callback.ICallbackListDataResponse
import com.techno.gapsi.data.room.db.AppDB
import com.techno.gapsi.data.room.entity.HistorySearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DbRepository(private val scope: CoroutineScope) {


    fun insertData(context : Context, user : HistorySearch){

        scope.launch {
            AppDB.getDataBase(context).userDAO().insert(user)
        }

    }




    fun getAllData(context : Context, callback :ICallbackListDataResponse) {

        scope.launch {
            callback.success( AppDB.getDataBase(context).userDAO().getAll() )
        }

    }



    fun isDbEnable(context : Context, callback : ICallbackBase){


        scope.launch {
            callback.success(AppDB.getDataBase(context).userDAO().hasItem())
        }



    }





}