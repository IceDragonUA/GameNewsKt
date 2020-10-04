package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.R
import com.evaluation.adapter.diffutils.AutoUpdatableAdapter
import com.evaluation.adapter.viewmodels.BaseViewModel
import com.evaluation.adapter.viewmodels.item.CardItemView
import com.evaluation.adapter.viewmodels.item.NoItemView
import com.evaluation.adapter.viewmodels.item.SliderItemView
import com.evaluation.model.NewsTabItem
import com.evaluation.model.room.NewsTableItem
import kotlinx.android.synthetic.main.news_view.view.*
import kotlin.properties.Delegates


/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
class CustomPageAdapter(private val keys: Array<String> = emptyArray()) : RecyclerView.Adapter<CustomPageAdapter.CustomPageViewHolder>() {

    var items: List<NewsTabItem> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomPageViewHolder =
        CustomPageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_view, parent, false))

    override fun onBindViewHolder(holder: CustomPageViewHolder, position: Int) {
        holder.bind(items, getItem(position))
    }

    private fun getItem(position: Int): List<NewsTableItem>? = items.find { it.id == keys[position] }?.news

    override fun getItemCount(): Int = keys.count()

    class CustomPageViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(items: List<NewsTabItem>, item: List<NewsTableItem>?) {
            val newsList: MutableList<BaseViewModel> = mutableListOf()
            val topNewsList: MutableList<NewsTableItem> = mutableListOf()

            // TODO: 04.10.2020 Uncomment when backend provide data
//            val topNewsList = item?.filter { it.top.toInt() > 0 && it.img.isNotEmpty() }?.take(SLIDE_LIMIT)
//
//            if (!topNewsList.isNullOrEmpty()) {
//                newsList.add(SliderItemView(topNewsList))
//            }

            // TODO: 04.10.2020 Remove when backend provide data
            items.forEach{
                it.news.forEach { newsTableItem ->
                    topNewsList.add(newsTableItem)
                }
            }
            val sliderList = topNewsList.filter { it.img.isNotEmpty() }.take(SLIDE_LIMIT)
            if (!sliderList.isNullOrEmpty()) {
                newsList.add(SliderItemView(sliderList))
            }

            item?.forEach { newsList.add(CardItemView(it)) }
            itemView.listView.adapter.items = newsList.ifEmpty {
                mutableListOf(NoItemView(itemView.context.resources.getString(R.string.result)))
            }
        }

        companion object {
            const val SLIDE_LIMIT = 5
        }
    }
}