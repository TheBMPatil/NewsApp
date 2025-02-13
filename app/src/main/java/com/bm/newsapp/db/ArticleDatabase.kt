package com.bm.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bm.newsapp.models.Article


@Database(
    entities = [Article::class], version = 1
)

@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDAO

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        private fun createDatabse(context: Context) = Room.databaseBuilder(
            context.applicationContext, ArticleDatabase::class.java, "article_db.db"
        ).build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabse(context).also {
                instance = it
            }
        }

    }
}
