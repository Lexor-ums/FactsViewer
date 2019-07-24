package com.example.factviewer.dagger.components

import com.example.factviewer.MainApplication
import com.example.factviewer.dagger.modules.ApplicationMainModule
import com.example.factviewer.dagger.modules.DatabaseModule
import com.example.factviewer.dagger.modules.NetworkModule
//import com.example.calculator.dagger.modules.ViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import android.app.Application
import com.example.factviewer.ui.presenters.DetailsPresenter
import com.example.factviewer.ui.presenters.FactsListPresenter
import dagger.BindsInstance



@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ApplicationMainModule::class,
    NetworkModule::class,
    DatabaseModule::class])

interface ApplicationMainComponent {
    fun inject(app: MainApplication)
    fun inject(factsListPresenter : FactsListPresenter)
    fun inject(detailsPresenter : DetailsPresenter)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationMainComponent
    }
}