package com.example.factviewer.ui.views

import com.arellomobile.mvp.MvpView

interface DetailView : MvpView{
    /**
     * событие отображения подробностей статьи
     * @param id - уникальный идентификатор статьи
     * @param content - содержание статьи
     */
    fun onShowDetails(id : String, content : String)
}