package com.e.selfadaptation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert
    suspend fun addNews(news: News)

    @Delete
    suspend fun deleteNews(news: News)

    @Query("SELECT * FROM news")
    suspend fun getAllNews(): List<News>
}

