package com.example.factviewer.ui.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.factviewer.MainApplication
import com.example.factviewer.domain.repository.AnimalRepository
import com.example.factviewer.ui.base.BaseMvpPresenter
import com.example.factviewer.ui.views.FactListView
import com.example.factviewer.utils.ANIMAL_CHOSEN
import com.example.factviewer.utils.Settings
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@InjectViewState
class FactsListPresenter : BaseMvpPresenter<FactListView>() {

    @Inject
    lateinit var repository: AnimalRepository

    private var isLoading = false

    private var animal = ""

    init {
        MainApplication.instance.getAppComponent()?.inject(this)
    }

    fun setAnimal(animal: String) {
        this.animal = animal
    }

    fun loadData() {
        if (isLoading)
            return
        isLoading = true
        viewState.onStartLoading()
        scope.launch {
            val facts = if (animal != ANIMAL_CHOSEN)
                repository.getAnimalFacts(animal, Settings.factsAmount)
            else
                repository.getLikedFacts()
            viewState.onFinishLoading()
            if (facts.isEmpty())
                viewState.showError()
            else
                viewState.addFactsList(facts)
            isLoading = false
        }
        viewState.onFinishLoading()
    }
}