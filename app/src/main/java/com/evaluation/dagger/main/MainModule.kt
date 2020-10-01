package com.evaluation.dagger.main

import com.evaluation.fragment.MainContract
import com.evaluation.fragment.MainPresenter
import com.evaluation.repository.MainRepository
import com.evaluation.repository.MainRepositoryImpl
import com.evaluation.worker.scheduler.SyncScheduler
import com.evaluation.worker.sync.SyncWorkHelper
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    @MainScope
    abstract fun repository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    @MainScope
    abstract fun presenter(mainPresenter: MainPresenter): MainContract.Presenter

    @Binds
    @MainScope
    abstract fun syncScheduler(syncWorkHelper: SyncWorkHelper): SyncScheduler

}