package com.example.factviewer.ui.views

import com.arellomobile.mvp.MvpView

interface LikeView : MvpView{
    /**
     * обработка событие нажатия на кнопку лайка
     * @param isLiked состояние кнопки
     */
    fun onLikeClick(isLiked : Boolean)
}