package com.techno.gapsi.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class HistorySearch(

    @PrimaryKey (autoGenerate = false) @ColumnInfo(name = "data") var data :String

)