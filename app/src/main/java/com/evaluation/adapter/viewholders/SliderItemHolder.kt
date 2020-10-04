package com.evaluation.adapter.viewholders

import android.view.View
import com.evaluation.adapter.viewmodels.item.SliderItemView
import kotlinx.android.synthetic.main.news_slider_item.view.*

class SliderItemHolder(itemView: View) : BaseViewHolder<SliderItemView>(itemView) {

    override fun bind(item: SliderItemView) {
        itemView.cardsSliderView.bind(item.newsList)
    }

}