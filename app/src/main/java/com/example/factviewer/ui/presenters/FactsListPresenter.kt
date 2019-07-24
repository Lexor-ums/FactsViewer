package com.example.factviewer.ui.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.factviewer.MainApplication
import com.example.factviewer.domain.repository.AnimalRepository
import com.example.factviewer.ui.base.BaseMvpPresenter
import com.example.factviewer.ui.views.FactListView
import com.example.factviewer.utils.Settings
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@InjectViewState
class FactsListPresenter : BaseMvpPresenter<FactListView>() {

    @Inject
    lateinit var  repository: AnimalRepository

    private var isLoading = false

    init {
        MainApplication.instance.getAppComponent()?.inject(this)
        loadData()
    }

    private fun loadData(){
        if(isLoading)
            return
        isLoading = true
        viewState.onStartLoading()
        scope.launch{
            val facts = repository.getAnimalFacts("cat", Settings.factsAmount)
            viewState.onFinishLoading()
            if(facts.isEmpty())
                viewState.showError()
            else
                viewState.addFactsList(facts)
        }
        viewState.onFinishLoading()
    }
}