package com.techno.gapsi.data.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.techno.gapsi.data.room.dao.UserHistoryDAO
import com.techno.gapsi.data.room.entity.HistorySearch

@Database(entities = [HistorySearch::class],version = 1)

abstract class AppDB : RoomDatabase(){

    abstract fun userDAO():UserHistoryDAO

    companion object{

        private var INSTANCE: AppDB?=null


        fun getDataBase(context: Context):AppDB{

            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext,AppDB::class.java,"DBGapsi").build()

            return INSTANCE!!
        }

        fun destroyInstance(){

            INSTANCE = null

        }
    }

}