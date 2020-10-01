package com.evaluation.fragment

import androidx.work.WorkManager
import com.evaluation.event.Event
import com.evaluation.event.bus.DomainBus
import com.evaluation.model.retrofit.NewsItem
import com.evaluation.model.room.NewsTableItem
import com.evaluation.presenter.BasePresenterImpl
import com.evaluation.repository.MainRepository
import com.evaluation.worker.sync.SyncWorkHelper.Companion.UPDATE_APP_WORK
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 15.04.2020
 */
class MainPresenter @Inject constructor(
    private val repository: MainRepository,
    private val updateBus: DomainBus
) : MainContract.Presenter, BasePresenterImpl<MainContract.View>() {

    override fun bindView(view: MainContract.View) {
        super.bindView(view)
        repository.syncData(UPDATE_INTERVAL)
    }

    override fun provideData() {
        compositeDisposable.add(
            provideUpdater()
        )
    }

    private fun provideUpdater(): Disposable {
        return updateBus.publisher()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onUpdateSuccess, ::onUpdateError)
    }

    private fun provideList(): Disposable {
        return repository
            .newsList()
            .doOnSubscribe { view?.showLoading() }
            .subscribe(::onLoadingSuccess, ::onLoadingError)
    }

    private fun onLoadingSuccess(list: List<NewsTableItem>) {
        view?.hideLoading()
        view?.showList(list)
    }

    private fun onLoadingError(error: Throwable) {
        view?.hideLoading()
        Timber.e(error, "Error load data")
    }

    private fun onUpdateSuccess(event: Event) {
        compositeDisposable.add(
            provideList()
        )
    }

    private fun onUpdateError(error: Throwable) {
        Timber.e(error, "Error update data")
    }

    companion object {
        private const val UPDATE_INTERVAL = 15
    }
}