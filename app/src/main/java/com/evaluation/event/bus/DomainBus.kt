package com.evaluation.event.bus

import com.evaluation.dagger.main.MainScope
import com.evaluation.event.Event
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@MainScope
class DomainBus @Inject constructor() {

    private fun createPublisher (): PublishSubject<Event> = PublishSubject.create()

    private val publisher = createPublisher()

    fun publisher() = publisher

    fun updated() = publisher.onNext(Event())

}
