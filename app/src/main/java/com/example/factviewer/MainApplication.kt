package com.example.factviewer

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.Resources
import com.example.calculator.utils.navigation.fragmentrouter.FragmentRouter
import com.example.factviewer.dagger.components.ApplicationMainComponent
import com.example.factviewer.dagger.components.DaggerApplicationMainComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var appResources: Resources
    private var appComponent: ApplicationMainComponent? = null

    override fun onCreate() {
        super.onCreate()
        appResources = resources
        instance = this
        appComponent = DaggerApplicationMainComponent.builder()
            .application(this)
            .build()
        appComponent!!.inject(this);
    }

    fun getRsources(): Resources {
        return appResources
    }

    fun getAppComponent(): ApplicationMainComponent? {
        return appComponent
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        lateinit var instance: MainApplication
            private set

        @SuppressLint("StaticFieldLeak")
        var fragmentRouter = FragmentRouter()
    }
}