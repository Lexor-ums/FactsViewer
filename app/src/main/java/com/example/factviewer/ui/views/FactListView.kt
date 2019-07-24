package com.example.factviewer.ui.views

import com.arellomobile.mvp.MvpView
import com.example.factviewer.domain.animalfact.AnimalFact

interface FactListView : MvpView {
    fun showError()
    fun hideError()
    fun onStartLoading()
    fun onFinishLoading()
    fun addFactsList(list : List<AnimalFact>)
}