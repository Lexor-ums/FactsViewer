package com.example.factviewer.ui.presenters

import com.arellomobile.mvp.InjectViewState
import com.example.factviewer.MainApplication
import com.example.factviewer.domain.repository.AnimalRepository
import com.example.factviewer.ui.base.BaseMvpPresenter
import com.example.factviewer.ui.views.LikeView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * презентер, обесепечивающий функционал нажатия на кнопку лайка
 */
@InjectViewState
class LikePresenter : BaseMvpPresenter<LikeView>(){
    @Inject
    lateinit var repository: AnimalRepository
    init {
        MainApplication.instance.getAppComponent()?.inject(this)
    }

    /**
     * обработка нажатия на кнопку лайка
     * @param id уникальный идентификаор записи
     * @param isLiked состояние
     */
    fun updateLike(id : String, isLiked : Boolean){
        GlobalScope.launch {
            repository.updateLike(id, isLiked)
        }
        viewState.onLikeClick(isLiked)
    }
}