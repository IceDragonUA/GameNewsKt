package com.evaluation.adapter.viewmodels.item

import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.adapter.viewmodels.BaseViewModel
import com.evaluation.model.room.NewsTableItem

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
data class SliderItemView(var newsList: List<NewsTableItem>) : BaseViewModel {

    override fun type(typesFactory: TypesFactory): Int = typesFactory.type(this)

}