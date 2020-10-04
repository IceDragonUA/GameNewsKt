package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.evaluation.adapter.diffutils.NewsDiffItemCallback
import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.adapter.viewholders.BaseViewHolder
import com.evaluation.adapter.viewmodels.BaseViewModel

class CustomListAdapter constructor(private val typeFactory: TypesFactory) :
    PagedListAdapter<BaseViewModel, BaseViewHolder<BaseViewModel>>(NewsDiffItemCallback()) {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseViewModel> =
        typeFactory.holder(viewType, LayoutInflater.from(parent.context).inflate(viewType, parent, false)) as BaseViewHolder<BaseViewModel>

    override fun onBindViewHolder(holder: BaseViewHolder<BaseViewModel>, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun getItemViewType(position: Int): Int = getItem(position)!!.type(typeFactory)

}