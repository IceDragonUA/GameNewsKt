package com.evaluation.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Vladyslav Havrylenko
 * @since 02.05.2020
 */
@Entity(tableName = "news")
data class NewsTableItem(
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null,
    val title :String,
    val type :String,
    val img :String,
    val click_url :String,
    val time :String,
    val top :String
)