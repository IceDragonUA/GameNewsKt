package com.evaluation.model.retrofit

import com.google.gson.annotations.SerializedName

/**
 * @author Vladyslav Havrylenko
 * @since 02.05.2020
 */
data class NewsItem(
    @SerializedName("title")
    val title :String,
    @SerializedName("type")
    val type :String,
    @SerializedName("img")
    val img :String,
    @SerializedName("click_url")
    val click_url :String,
    @SerializedName("time")
    val time :String,
    @SerializedName("top")
    val top :String
)