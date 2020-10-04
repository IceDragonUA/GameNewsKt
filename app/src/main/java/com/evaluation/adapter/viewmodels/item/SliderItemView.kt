package com.evaluation.adapter.viewmodels.item

import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.adapter.viewmodels.BaseViewModel
import com.evaluation.model.room.NewsTableItem
import com.evaluation.utils.SLIDER

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
data class SliderItemView(override var id: String = SLIDER, var newsList: List<NewsTableItem>) : BaseViewModel {

    override fun type(typesFactory: TypesFactory): Int = typesFactory.type(this)

}