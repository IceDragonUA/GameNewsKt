package com.evaluation.adapter.viewmodels.item

import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.adapter.viewmodels.BaseViewModel
import com.evaluation.utils.NO_ITEM

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
data class NoItemView(override var id: String = NO_ITEM, var title: String) : BaseViewModel {

    override fun type(typesFactory: TypesFactory): Int = typesFactory.type(this)

}