package com.evaluation.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.R
import com.evaluation.adapter.CustomSliderAdapter
import com.evaluation.model.room.NewsTableItem
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.cards_slider_view.view.*

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
class CustomSliderView : FrameLayout {

    val adapter: CustomSliderAdapter

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflate(context, R.layout.cards_slider_view, this)
        adapter = CustomSliderAdapter()
        sliderView.adapter = adapter
        (sliderView.getChildAt(0) as RecyclerView).addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
        TabLayoutMediator(sliderDotsContainer, sliderView) { _, _ -> }.attach()
    }

    fun bind(items: List<NewsTableItem>) {
        adapter.items = items
    }

}