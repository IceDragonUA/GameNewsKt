package com.evaluation.adapter.viewmodels

import com.evaluation.adapter.factory.TypesFactory

interface BaseViewModel {

    var id: String

    fun type(typesFactory: TypesFactory): Int

}