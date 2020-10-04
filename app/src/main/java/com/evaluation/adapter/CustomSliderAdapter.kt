package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.R
import com.evaluation.adapter.diffutils.AutoUpdatableAdapter
import com.evaluation.model.room.NewsTableItem
import com.evaluation.utils.loadFromUrl
import kotlinx.android.synthetic.main.slider_item.view.*
import kotlin.properties.Delegates


/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
class CustomSliderAdapter : RecyclerView.Adapter<CustomSliderAdapter.CustomSliderViewHolder>(), AutoUpdatableAdapter {

    var items: List<NewsTableItem> by Delegates.observable(mutableListOf()) { _, old, new ->
        autoNotify(old, new) { o, n -> o.hashCode() == n.hashCode() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomSliderViewHolder =
        CustomSliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slider_item, parent, false))

    override fun onBindViewHolder(holder: CustomSliderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): NewsTableItem = items[position]

    override fun getItemCount(): Int = items.count()

    class CustomSliderViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: NewsTableItem) {
            itemView.sliderImage.loadFromUrl(item.img)
            itemView.sliderTitle.text = item.title
            itemView.sliderSite.text = item.click_url
            itemView.sliderTime.text = item.time
        }
    }
}