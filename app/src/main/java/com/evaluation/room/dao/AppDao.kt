package com.evaluation.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evaluation.model.room.NewsTableItem
import io.reactivex.Single

@Dao
interface AppDao {

    @Query("SELECT * FROM news")
    fun newsList(): Single<List<NewsTableItem>>

    @Query("SELECT * FROM news WHERE title LIKE '%' || :title || '%'")
    fun newsFilterList(title: String): Single<List<NewsTableItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsList: NewsTableItem)

    @Query("DELETE FROM news")
    fun deleteNewsList()

}