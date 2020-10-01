package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.R
import com.evaluation.adapter.CustomListAdapter.ListAdapterHolder
import com.evaluation.model.room.NewsTableItem
import kotlinx.android.synthetic.main.list_item.view.*
import kotlin.properties.Delegates

class CustomListAdapter : RecyclerView.Adapter<ListAdapterHolder>() {

    var mNewsList: List<NewsTableItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ListAdapterHolder =
        ListAdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(listAdapterHolder: ListAdapterHolder, position: Int) {
        listAdapterHolder.bind(getItem(position))
    }

    private fun getItem(position: Int): NewsTableItem {
        return mNewsList[position]
    }

    override fun getItemCount(): Int {
        return mNewsList.size
    }

    class ListAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(newsItem: NewsTableItem) {
            itemView.name.text = newsItem.title
        }
    }
}