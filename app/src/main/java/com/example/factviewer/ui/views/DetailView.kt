package com.example.factviewer.ui.views

import com.arellomobile.mvp.MvpView

interface DetailView : MvpView{
    fun onShowDetails(id : String, content : String)
}