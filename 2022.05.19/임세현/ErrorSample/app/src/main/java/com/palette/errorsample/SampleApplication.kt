package com.palette.errorsample

import android.app.Application

class SampleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        setCrashHandler()
    }

    private fun setCrashHandler() {
        val crashlyticsExceptionHandler = Thread.getDefaultUncaughtExceptionHandler() ?: return
        Thread.setDefaultUncaughtExceptionHandler(
            SampleExceptionHandler(
                this,
                crashlyticsExceptionHandler
            )
        )
    }
}