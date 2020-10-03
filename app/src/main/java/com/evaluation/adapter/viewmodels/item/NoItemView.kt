package com.evaluation.adapter.viewmodels.item

import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.adapter.viewmodels.BaseViewModel

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
data class NoItemView(var title: String) : BaseViewModel {

    override fun type(typesFactory: TypesFactory): Int = typesFactory.type(this)

}