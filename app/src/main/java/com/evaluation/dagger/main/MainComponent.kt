package com.evaluation.dagger.main

import com.evaluation.fragment.MainFragment
import com.evaluation.worker.UpdateWorker
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {

    fun inject (fragment: MainFragment)

    fun inject(syncWorkHelper: UpdateWorker)

}