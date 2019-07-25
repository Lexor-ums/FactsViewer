package com.example.factviewer.ui.presenters

import com.arellomobile.mvp.InjectViewState
import com.example.factviewer.MainApplication
import com.example.factviewer.domain.repository.AnimalRepository
import com.example.factviewer.ui.base.BaseMvpPresenter
import com.example.factviewer.ui.views.FactListView
import com.example.factviewer.utils.ANIMAL_CHOSEN
import com.example.factviewer.utils.Settings
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * презентер view оботрадения списка статей
 */
@InjectViewState
class FactsListPresenter : BaseMvpPresenter<FactListView>() {

    @Inject
    lateinit var repository: AnimalRepository

    private var isLoading = false

    private var animal = ""

    init {
        MainApplication.instance.getAppComponent()?.inject(this)
    }

    /**
     * Установка типа животного, факты о котором хотим знать
     * @param animal - тип животного. Константы храняться в утилсах
     */
    fun setAnimal(animal: String) {
        this.animal = animal
    }

    /**
     * загрузка фактов о животном
     */
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