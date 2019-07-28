package com.example.factviewer.presentation.factslistfragment

import com.arellomobile.mvp.MvpView
import com.example.factviewer.data.application.AnimalFact

interface FactListView : MvpView {
    /**
     * отображение ошибки
     */
    fun showError()

    /**
     * сокрытие сообщения об ошибки
     */

    fun hideError()

    /**
     * событие начала загрузки списка статей
     */

    fun onStartLoading()

    /**
     * событие окончания загрузки списка статей
     */

    fun onFinishLoading()

    /**
     * событие добавления списка загруженных статей в recyclerView
     * @param list - список статей
     */
    fun addFactsList(list : List<AnimalFact>)
}