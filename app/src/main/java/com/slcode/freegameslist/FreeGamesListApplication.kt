package com.slcode.freegameslist

import android.app.Application
import com.slcode.freegameslist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FreeGamesListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FreeGamesListApplication)
            modules(appModule)
        }
    }
}