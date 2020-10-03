package com.evaluation.adapter.factory

import android.view.View
import com.evaluation.R
import com.evaluation.adapter.viewholders.BaseViewHolder
import com.evaluation.adapter.viewholders.NoItemHolder
import com.evaluation.adapter.viewholders.SliderItemHolder
import com.evaluation.adapter.viewholders.CardItemHolder
import com.evaluation.adapter.viewmodels.item.CardItemView
import com.evaluation.adapter.viewmodels.item.NoItemView
import com.evaluation.adapter.viewmodels.item.SliderItemView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TypesFactoryImpl @Inject constructor() : TypesFactory {

    override fun type(item: NoItemView): Int = R.layout.news_no_item

    override fun type(item: SliderItemView): Int = R.layout.news_slider_item

    override fun type(item: CardItemView): Int = R.layout.news_card_item

    @Suppress("UNCHECKED_CAST")
    override fun holder(type: Int, view: View): BaseViewHolder<*> {
        return when (type) {
            R.layout.news_no_item -> NoItemHolder(view)
            R.layout.news_slider_item -> SliderItemHolder(view)
            R.layout.news_card_item -> CardItemHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }
}