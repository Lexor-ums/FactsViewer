package com.example.factviewer.ui.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.factviewer.ui.views.ItemSelectorView

@InjectViewState
class ItemSelectorPresenter : MvpPresenter<ItemSelectorView>() {
    fun onListItemSelected(position : Int, id : String){
        viewState.onItemSelected(position, id)
    }
}