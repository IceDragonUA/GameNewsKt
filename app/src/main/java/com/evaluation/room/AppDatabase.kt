package com.evaluation.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evaluation.model.retrofit.NewsItem
import com.evaluation.model.room.NewsTableItem
import com.evaluation.room.dao.AppDao
import com.evaluation.room.migrations.DATABASE_VERSION

@Database(
    entities = [
        NewsTableItem::class
    ], version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

}






