package com.evaluation.adapter.data

import android.content.Context
import androidx.paging.PositionalDataSource
import com.evaluation.R
import com.evaluation.adapter.CustomPageAdapter.CustomPageViewHolder.Companion.SLIDE_LIMIT
import com.evaluation.adapter.viewmodels.BaseViewModel
import com.evaluation.adapter.viewmodels.item.CardItemView
import com.evaluation.adapter.viewmodels.item.NoItemView
import com.evaluation.adapter.viewmodels.item.SliderItemView
import com.evaluation.model.room.NewsTableItem
import com.evaluation.utils.defIfNull

/**
 * @author Vladyslav Havrylenko
 * @since 04.10.2020
 */
class ListDataSource(context: Context, item: List<NewsTableItem>?) : PositionalDataSource<BaseViewModel>() {

    private val newsList: MutableList<BaseViewModel> = mutableListOf()

    init {
        // TODO: 05.10.2020 UNCOMMENT after backend data filling
        val topNewsList = item?.filter { /*it.top.toInt() > 0 && */it.img.isNotEmpty() }?.take(SLIDE_LIMIT)
        if (!topNewsList.isNullOrEmpty()) {
            newsList.add(SliderItemView(newsList = topNewsList))
        }
        item?.forEach { newsList.add(CardItemView(id = it.id.defIfNull().toString(), news = it)) }
        newsList.ifEmpty {
            newsList.add(NoItemView(title = context.resources.getString(R.string.result)))
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<BaseViewModel>) {
        val startPosition = params.requestedStartPosition
        val endPosition = if (params.requestedLoadSize > newsList.size) newsList.size else params.requestedLoadSize
        callback.onResult(newsList.subList(startPosition, endPosition), 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<BaseViewModel>) {
        val startPosition = params.startPosition
        val endPosition = if (params.startPosition + params.loadSize > newsList.size) newsList.size else params.startPosition + params.loadSize
        if (startPosition < endPosition) callback.onResult(newsList.subList(startPosition, endPosition))
    }
}