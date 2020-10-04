package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.adapter.diffutils.AutoUpdatableAdapter
import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.adapter.viewholders.BaseViewHolder
import com.evaluation.adapter.viewmodels.BaseViewModel
import kotlin.properties.Delegates

class CustomListAdapter constructor(private val typeFactory: TypesFactory) : RecyclerView.Adapter<BaseViewHolder<BaseViewModel>>(), AutoUpdatableAdapter {

    var items: MutableList<BaseViewModel> by Delegates.observable(mutableListOf()) { _, old, new ->
        autoNotify(old, new) { o, n -> o.hashCode() == n.hashCode() }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseViewModel> {
        return typeFactory.holder(viewType, LayoutInflater.from(parent.context).inflate(viewType, parent, false)) as BaseViewHolder<BaseViewModel>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseViewModel>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int = items[position].type(typeFactory)

    override fun getItemCount(): Int = items.count()

}