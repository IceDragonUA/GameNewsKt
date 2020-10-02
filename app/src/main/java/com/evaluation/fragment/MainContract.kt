package com.evaluation.fragment

import com.evaluation.model.room.NewsTableItem
import com.evaluation.presenter.BasePresenter
import com.evaluation.presenter.BaseView

/**
 * @author Vladyslav Havrylenko
 * @since 15.04.2020
 */
interface MainContract {

    interface View : BaseView {

        fun showList(newsItem: List<NewsTableItem>)

    }

    interface Presenter : BasePresenter<View> {

        fun provideData(word: String? = null)

    }
}