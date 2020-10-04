package com.evaluation.model

import com.evaluation.model.room.NewsTableItem

/**
 * @author Vladyslav Havrylenko
 * @since 04.10.2020
 */
data class NewsTabItem(
    val id :String,
    val news :List<NewsTableItem>,
)