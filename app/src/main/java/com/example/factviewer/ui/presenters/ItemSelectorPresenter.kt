package com.example.factviewer.ui.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.factviewer.ui.views.ItemSelectorView

/**
 * презентер, обеспечивающий функционал выбора статьи из списка
 */
@InjectViewState
class ItemSelectorPresenter : MvpPresenter<ItemSelectorView>() {
    /**
     * обработка события выбора из списка
     * @param id - уникальный идентификатор
     * @param position - позиция в списке
     */
    fun onListItemSelected(position : Int, id : String){
        viewState.onItemSelected(position, id)
    }
}