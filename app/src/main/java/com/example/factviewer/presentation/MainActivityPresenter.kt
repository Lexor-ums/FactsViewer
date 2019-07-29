package com.example.factviewer.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.calculator.utils.navigation.fragmentrouter.Screens
import com.example.factviewer.MainApplication.Companion.fragmentRouter

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>(){


    fun onListFragmentSelected(animal : String){
        fragmentRouter.navigateTo(Screens.FRAGMENTS.FACT_LIST_FRAGMENT, animal)
    }

    fun onDetailsSelected(id : String){
        fragmentRouter.replace(Screens.FRAGMENTS.DETAILS_FRAGMENT, id)
    }
}