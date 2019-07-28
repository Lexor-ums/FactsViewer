package com.example.factviewer.presentation.factdetailsfragment

import com.arellomobile.mvp.MvpView

interface DetailView : MvpView{
    /**
     * событие отображения подробностей статьи
     * @param id - уникальный идентификатор статьи
     * @param content - содержание статьи
     */
    fun onShowDetails(id : String, content : String)
}