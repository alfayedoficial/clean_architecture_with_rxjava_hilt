package com.fourdev.cleanarchitecturewithrxjavahilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 8:22 PM
 */
@HiltAndroidApp
class App: Application() {


    override fun onCreate() {
        super.onCreate()
        // setup timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
