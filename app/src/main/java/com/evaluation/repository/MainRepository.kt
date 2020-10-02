package com.evaluation.repository

import com.evaluation.model.room.NewsTableItem
import io.reactivex.Single

/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
interface MainRepository {

    fun newsList(word: String?): Single<List<NewsTableItem>>

    fun syncData(intervalMinutes: Int)

}