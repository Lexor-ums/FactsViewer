package com.example.factviewer.ui.views

import com.arellomobile.mvp.MvpView

interface LikeView : MvpView{
    fun onLikeClick(isLiked : Boolean)
}