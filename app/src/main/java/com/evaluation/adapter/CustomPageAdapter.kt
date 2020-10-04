package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.R
import com.evaluation.adapter.data.ListDataSource
import com.evaluation.model.NewsTabItem
import com.evaluation.model.room.NewsTableItem
import com.evaluation.utils.MainThreadExecutor
import kotlinx.android.synthetic.main.news_view.view.*
import java.util.concurrent.Executors
import kotlin.properties.Delegates


/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
class CustomPageAdapter(private val keys: Array<String> = emptyArray()) :
    RecyclerView.Adapter<CustomPageAdapter.CustomPageViewHolder>() {

    var items: List<NewsTabItem> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomPageViewHolder =
        CustomPageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_view, parent, false))

    override fun onBindViewHolder(holder: CustomPageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): List<NewsTableItem>? =
        items.find { it.id == keys[position] }?.news

    override fun getItemCount(): Int = keys.count()

    class CustomPageViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: List<NewsTableItem>?) {
            val dataSource = ListDataSource(itemView.context, item)

            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_LIMIT)
                .build()

            val pagedList = PagedList.Builder(dataSource, config)
                .setNotifyExecutor(MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()

            itemView.listView.scrollToPosition(DEFAULT_POSITION)
            itemView.listView.adapter.submitList(pagedList)
        }

        companion object {
            const val DEFAULT_POSITION = 0
            const val SLIDE_LIMIT = 5
            const val PAGE_LIMIT = 5
        }
    }
}