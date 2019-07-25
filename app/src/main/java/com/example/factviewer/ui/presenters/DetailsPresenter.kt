package com.example.factviewer.ui.presenters

import com.arellomobile.mvp.InjectViewState
import com.example.factviewer.MainApplication
import com.example.factviewer.domain.repository.AnimalRepository
import com.example.factviewer.ui.base.BaseMvpPresenter
import com.example.factviewer.ui.views.DetailView
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Презентер view отображения деталей статьи
 */
@InjectViewState
class DetailsPresenter : BaseMvpPresenter<DetailView>(){
    @Inject
    lateinit var repository: AnimalRepository

    init {
        MainApplication.instance.getAppComponent()?.inject(this)

    }

    /**
     * Загрузка статьи
     * @param id - уникальный идентификатор статьи
     */
    fun loadDetail(id : String){
        scope.launch {
            val request = repository.getById(id)
            if(request != null)
                viewState.onShowDetails(id, request)
        }
    }
}