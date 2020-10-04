package com.evaluation.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor


/**
 * @author Vladyslav Havrylenko
 * @since 04.10.2020
 */
class MainThreadExecutor(private val handler: Handler = Handler(Looper.getMainLooper())) : Executor {

    override fun execute(command: Runnable) {
        handler.post(command)
    }
}