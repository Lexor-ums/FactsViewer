package com.example.factviewer.presentation.factdetailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.factviewer.R
import kotlinx.android.synthetic.main.fact_details_fragment.*

class FactDetailsFragment(private val id: String) : MvpAppCompatFragment(),
    DetailView {

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    override fun onResume() {
        super.onResume()
        presenter.loadDetail(id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fact_details_fragment, container, false)
    }

    override fun onShowDetails(id: String, content: String) {
        textViewId.text = id
        textViewContent.text = content
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}