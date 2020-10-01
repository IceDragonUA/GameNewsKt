package com.evaluation

import android.app.Application
import com.evaluation.dagger.AppComponent
import com.evaluation.dagger.DaggerAppComponent
import com.evaluation.dagger.main.MainComponent
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins

class App : Application() {

    companion object {

        lateinit var appComponent: AppComponent

        var mainComponent: MainComponent? = null
            get() {
                if (field == null)
                    field = appComponent.mainComponent()
                return field
            }

        fun clearComponent() {
            mainComponent = null
        }
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .app(this)
                .build()

        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                System.err.println(e.message)
            } else {
                Thread.currentThread().also { thread ->
                    thread.uncaughtExceptionHandler.uncaughtException(thread, e)
                }
            }
        }
    }

}