package com.evaluation.repository

import com.evaluation.mapper.NewsMapper
import com.evaluation.model.retrofit.NewsItem
import com.evaluation.model.room.NewsTableItem
import com.evaluation.network.RestAdapter
import com.evaluation.room.dao.AppDao
import com.evaluation.worker.scheduler.SyncScheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
class MainRepositoryImpl @Inject constructor(
    private val appRest: RestAdapter,
    private val appDao: AppDao,
    private val mapper: NewsMapper,
    private val syncScheduler: SyncScheduler
) : MainRepository {

    override fun newsList(): Single<List<NewsTableItem>> {
        return appRest.api.newsList()
            .map {
                appDao.deleteNewsList()
                it.forEach { newsItem ->
                    appDao.insertNews(mapper.toTableItem(newsItem))
                }
            }
            .flatMap {
                appDao.newsList()
            }
            .onErrorResumeNext {
                appDao.newsList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun syncData(intervalMinutes: Int) {
        syncScheduler.startSchedule(intervalMinutes.toLong(), TimeUnit.MINUTES)
    }

}
