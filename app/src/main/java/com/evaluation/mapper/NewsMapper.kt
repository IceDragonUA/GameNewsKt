package com.evaluation.mapper

import com.evaluation.model.retrofit.NewsItem
import com.evaluation.model.room.NewsTableItem
import com.evaluation.utils.defIfNull
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 01.10.2020
 */
class NewsMapper @Inject constructor() {

    fun toTableItem(item: NewsItem): NewsTableItem {
        return item.let {
            NewsTableItem(
                title = it.title.defIfNull(),
                type = it.type.defIfNull(),
                img = it.img.defIfNull(),
                click_url = it.click_url.defIfNull(),
                time = it.time.defIfNull(),
                top = it.top.defIfNull()
            )
        }
    }
}