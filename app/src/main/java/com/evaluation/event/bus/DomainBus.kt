package com.evaluation.event.bus

import com.evaluation.dagger.main.MainScope
import com.evaluation.event.Event
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@MainScope
class DomainBus @Inject constructor() {

    private fun createPublisher (): PublishSubject<Event> = PublishSubject.create()

    private var word: String? = null

    private val publisher = createPublisher()

    fun publisher(word: String?): PublishSubject<Event> {
        this.word = word
        return publisher
    }

    fun updated() = publisher.onNext(Event(word))

}
