package com.example.factviewer.ui.factdetailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.factviewer.R
import com.example.factviewer.ui.presenters.DetailsPresenter
import com.example.factviewer.ui.views.DetailView
import kotlinx.android.synthetic.main.fact_details_fragment.*

class FactDetailsFragment(private val id : String) : MvpAppCompatFragment(), DetailView{

    @InjectPresenter
    lateinit var presenter : DetailsPresenter

    override fun onResume() {
        super.onResume()
        presenter.loadDetail(id)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fact_details_fragment, container, false)
        return view
    }

    override fun onShowDetails(id: String, content: String) {
        println("onShowDetails $id")
        textViewId.text = id
        textViewContent.text = content
    }
}