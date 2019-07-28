package com.example.factviewer.presentation.common

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.factviewer.presentation.common.ItemSelectorView

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