package com.e.selfadaptation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var newsId: Int = 0,

    @ColumnInfo(name = "image")
    var newsImage: String? = null,

    @ColumnInfo(name = "title")
    var newsTitle: String? = null,

    @ColumnInfo(name = "text")
    var newsText: String? = null,

    @ColumnInfo(name = "date")
    var newsDate: String? = null
)