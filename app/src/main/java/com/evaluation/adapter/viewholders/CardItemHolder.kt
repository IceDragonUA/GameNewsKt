package com.evaluation.adapter.viewholders

import android.view.View
import com.evaluation.adapter.viewmodels.item.CardItemView
import com.evaluation.utils.loadFromUrl
import kotlinx.android.synthetic.main.item_content.view.*


class CardItemHolder(itemView: View) : BaseViewHolder<CardItemView>(itemView) {

    override fun bind(item: CardItemView) {
        itemView.image.loadFromUrl(item.news.img)
        itemView.title.text = item.news.title
        itemView.site.text = item.news.click_url
        itemView.time.text = item.news.time
    }

}