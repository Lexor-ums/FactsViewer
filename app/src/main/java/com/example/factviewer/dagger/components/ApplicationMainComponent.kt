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
import com.example.factviewer.presentation.factdetailsfragment.DetailsPresenter
import com.example.factviewer.presentation.factslistfragment.FactsListPresenter
import com.example.factviewer.presentation.common.LikePresenter
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
    fun inject(likesPresenter : LikePresenter)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationMainComponent
    }
}