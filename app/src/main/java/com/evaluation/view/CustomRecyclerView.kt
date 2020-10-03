package com.evaluation.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.adapter.CustomListAdapter

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
class CustomRecyclerView : RecyclerView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        layoutManager = LinearLayoutManager(context)
        itemAnimator = DefaultItemAnimator()
        adapter = CustomListAdapter()
    }

    override fun getAdapter(): CustomListAdapter =
        super.getAdapter() as CustomListAdapter
}