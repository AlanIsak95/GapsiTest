package com.techno.gapsi.data.room.dao

import androidx.room.Dao
import androidx.room.*
import com.techno.gapsi.data.room.entity.HistorySearch

@Dao
interface UserHistoryDAO {


    @Query("SELECT * FROM HistorySearch")
    suspend fun getAll(): List<HistorySearch>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historySearch : HistorySearch)


    @Query("SELECT EXISTS(SELECT * FROM HistorySearch)")
    suspend fun hasItem(): Boolean


}