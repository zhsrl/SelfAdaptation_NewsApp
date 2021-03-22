package com.e.selfadaptation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var newsId: Int = 0,
//
//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    var newsImage: String? = null,

    @ColumnInfo(name = "title")
    var newsTitle: String,

    @ColumnInfo(name = "text")
    var newsText: String,

    @ColumnInfo(name = "date")
    var newsDate: String
)