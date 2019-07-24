package com.example.factviewer.dagger.modules

import android.app.Application
import android.content.Context
import com.example.factviewer.MainApplication
import dagger.Module

@Module
class ApplicationMainModule(private val app : MainApplication){
    fun provideApplication() : Application{
        return app
    }
    fun provideContext() : Context{
        return app
    }
}