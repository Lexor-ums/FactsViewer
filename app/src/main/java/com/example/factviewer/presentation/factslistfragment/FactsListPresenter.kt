package com.example.factviewer.presentation.factslistfragment

import com.arellomobile.mvp.InjectViewState
import com.example.factviewer.MainApplication
import com.example.factviewer.data.application.AnimalFact
import com.example.factviewer.data.repository.DBAnimalRepository
import com.example.factviewer.domain.interactors.DataAccessInteractor
import com.example.factviewer.presentation.base.BaseMvpPresenter
import com.example.factviewer.utils.ANIMAL_CHOSEN
import com.example.factviewer.utils.Settings
import com.example.factviewer.utils.events.Events
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

/**
 * презентер view оботрадения списка статей
 */
@InjectViewState
class FactsListPresenter : BaseMvpPresenter<FactListView>() {

    @Inject
    lateinit var interactor: DataAccessInteractor

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
            if (animal != ANIMAL_CHOSEN)
                interactor.getAnimalFacts(animal, Settings.factsAmount)
            else
                interactor.getLikedFacts()
        viewState.onFinishLoading()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFactsListLoaded(event : Events.OnFactsListReceived){
        viewState.onFinishLoading()
        if (event.list.isEmpty())
            viewState.showError()
        else
            viewState.addFactsList(event.list)
        isLoading = false
    }

    fun onStart(){
        EventBus.getDefault().register(this)
    }
    fun onStop(){
        EventBus.getDefault().unregister(this)
    }
}