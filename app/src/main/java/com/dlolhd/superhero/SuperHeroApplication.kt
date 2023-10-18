package com.dlolhd.superhero

import android.app.Application
import com.dlolhd.superhero.data.AppContainer
import com.dlolhd.superhero.data.DefaultAppContainer

class SuperHeroApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}