package com.evaluation.adapter.factory

import android.view.View
import com.evaluation.adapter.viewholders.BaseViewHolder
import com.evaluation.adapter.viewmodels.item.CardItemView
import com.evaluation.adapter.viewmodels.item.NoItemView
import com.evaluation.adapter.viewmodels.item.SliderItemView

interface TypesFactory {

    fun type(item: NoItemView): Int

    fun type(item: SliderItemView): Int

    fun type(item: CardItemView): Int

    fun holder(type: Int, view: View): BaseViewHolder<*>

}