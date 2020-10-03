package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.R
import com.evaluation.adapter.viewmodels.BaseViewModel
import com.evaluation.adapter.viewmodels.item.CardItemView
import com.evaluation.adapter.viewmodels.item.NoItemView
import com.evaluation.adapter.viewmodels.item.SliderItemView
import com.evaluation.model.room.NewsTableItem
import kotlinx.android.synthetic.main.news_view.view.*
import kotlin.properties.Delegates


/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
class CustomPageAdapter(private val keys: Array<String>) : RecyclerView.Adapter<CustomPageAdapter.CustomPageViewHolder>() {

    var tabs: Map<String, List<NewsTableItem>> by Delegates.observable(emptyMap()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomPageViewHolder =
        CustomPageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_view, parent, false))

    override fun onBindViewHolder(holder: CustomPageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): List<NewsTableItem>? {
        return tabs[keys[position]]
    }

    override fun getItemCount(): Int {
        return keys.size
    }

    class CustomPageViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: List<NewsTableItem>?) {
            val newsList: MutableList<BaseViewModel> = mutableListOf()
            val topNewsList = item?.filter { it.top.toInt() > 0 && it.img.isNotEmpty() }

            if (!topNewsList.isNullOrEmpty()) {
                newsList.add(SliderItemView(topNewsList))
            }

            item?.forEach { newsList.add(CardItemView(it)) }
            itemView.listView.adapter.items = newsList.ifEmpty {
                mutableListOf((NoItemView(itemView.context.resources.getString(R.string.result))))
            }
        }
    }
}