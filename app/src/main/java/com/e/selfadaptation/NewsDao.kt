package com.e.selfadaptation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert
    fun addNews(news: News)

    @Delete
    fun deleteNews(news: News)

    @Query("SELECT * FROM news")
    fun getAllNews(): List<News>
}

