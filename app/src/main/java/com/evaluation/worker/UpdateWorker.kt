package com.evaluation.worker

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.evaluation.App
import com.evaluation.event.bus.DomainBus
import io.reactivex.Single
import javax.inject.Inject

class UpdateWorker(context: Context, params: WorkerParameters) : RxWorker(context, params) {

    @Inject
    lateinit var domainBus: DomainBus

    init {
        App.mainComponent?.inject(this)
    }

    override fun createWork(): Single<Result> {
        domainBus.updated()
        return Single.just(Result.success())
    }

}