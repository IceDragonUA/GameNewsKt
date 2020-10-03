package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.R
import com.evaluation.model.room.NewsTableItem
import kotlinx.android.synthetic.main.list_view.view.*
import java.util.*
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
        CustomPageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false))

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
            itemView.listView.adapter.newsList = item ?: emptyList()
        }
    }
}