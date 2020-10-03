package com.evaluation.adapter.viewmodels

import com.evaluation.adapter.factory.TypesFactory

interface BaseViewModel {

    fun type(typesFactory: TypesFactory): Int

}