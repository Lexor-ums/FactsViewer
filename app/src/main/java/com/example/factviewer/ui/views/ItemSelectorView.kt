package com.example.factviewer.ui.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface ItemSelectorView : MvpView{
    /**
     * слбытие выбора статьи из списка
     * @param id - уникальный идентификатор статьи
     * @param position - позиция в списке
     */
    fun onItemSelected(position : Int, id : String)
}