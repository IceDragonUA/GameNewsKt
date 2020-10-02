package com.evaluation.fragment

import com.evaluation.event.Event
import com.evaluation.event.bus.DomainBus
import com.evaluation.model.room.NewsTableItem
import com.evaluation.presenter.BasePresenterImpl
import com.evaluation.repository.MainRepository
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

    override fun provideData(word: String?) {
        compositeDisposable.clear()
        compositeDisposable.addAll(
            provideList(word),
            provideUpdater(word)
        )
    }

    private fun provideUpdater(word: String?): Disposable {
        return updateBus.publisher(word)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onUpdateSuccess, ::onUpdateError)
    }

    private fun provideList(word: String?): Disposable {
        return repository
            .newsList(word)
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
            provideList(event.word)
        )
    }

    private fun onUpdateError(error: Throwable) {
        Timber.e(error, "Error update data")
    }

    companion object {
        private const val UPDATE_INTERVAL = 15
    }
}