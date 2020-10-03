package com.evaluation.adapter.viewholders

import android.view.View
import com.evaluation.adapter.viewmodels.item.NoItemView
import kotlinx.android.synthetic.main.news_no_item.view.*

class NoItemHolder(itemView: View) : BaseViewHolder<NoItemView>(itemView) {

    override fun bind(item: NoItemView) {
        itemView.result.text = item.title
    }

}