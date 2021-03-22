package com.e.selfadaptation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.internal.synchronized

@Database(entities = [News::class], version = 1, exportSchema = true)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao
}

object DatabaseProvider{
    val DATABASE_NAME = "news"

    private var newsDatabase: NewsDatabase? = null

    fun getNewsDatabase(context: Context): NewsDatabase{
        kotlin.synchronized(this){
            var instance = newsDatabase

            if(instance == null){
                instance = Room.databaseBuilder(context.applicationContext,
                    NewsDatabase::class.java,
                    DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                    .build()

                newsDatabase = instance
            }

            return instance
        }
    }


}