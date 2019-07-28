package com.example.factviewer.presentation.common

import com.arellomobile.mvp.InjectViewState
import com.example.factviewer.MainApplication
import com.example.factviewer.domain.interactors.DataAccessInteractor
import com.example.factviewer.presentation.base.BaseMvpPresenter
import javax.inject.Inject

/**
 * презентер, обесепечивающий функционал нажатия на кнопку лайка
 */
@InjectViewState
class LikePresenter : BaseMvpPresenter<LikeView>() {
    @Inject
    lateinit var interactor: DataAccessInteractor

    init {
        MainApplication.instance.getAppComponent()?.inject(this)
    }

    /**
     * обработка нажатия на кнопку лайка
     * @param id уникальный идентификаор записи
     * @param isLiked состояние
     */
    fun updateLike(id: String, isLiked: Boolean) {
        interactor.updateLike(id, isLiked)
        viewState.onLikeClick(isLiked)
    }
}