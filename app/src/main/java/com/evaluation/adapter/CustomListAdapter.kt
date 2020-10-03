package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.R
import com.evaluation.adapter.CustomListAdapter.ListAdapterHolder
import com.evaluation.model.room.NewsTableItem
import com.evaluation.utils.loadFromUrl
import kotlinx.android.synthetic.main.item_content.view.*
import kotlin.properties.Delegates

class CustomListAdapter : RecyclerView.Adapter<ListAdapterHolder>() {

    var newsList: List<NewsTableItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ListAdapterHolder =
        ListAdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(listAdapterHolder: ListAdapterHolder, position: Int) {
        listAdapterHolder.bind(getItem(position))
    }

    private fun getItem(position: Int): NewsTableItem {
        return newsList[position]
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ListAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(newsItem: NewsTableItem) {
            itemView.image.loadFromUrl(newsItem.img)
            itemView.title.text = newsItem.title
            itemView.site.text = newsItem.click_url
            itemView.time.text = newsItem.time
        }
    }
}