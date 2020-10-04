package com.evaluation.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.evaluation.adapter.viewmodels.BaseViewModel

/**
 * @author Vladyslav Havrylenko
 * @since 05.10.2020
 */
class NewsDiffItemCallback : DiffUtil.ItemCallback<BaseViewModel>() {

    override fun areItemsTheSame(oldItem: BaseViewModel, newItem: BaseViewModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BaseViewModel, newItem: BaseViewModel): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}