package com.example.factviewer.ui.factslistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.calculator.utils.FragmentUtils
import com.example.factviewer.R
import com.example.factviewer.domain.animalfact.AnimalFact
import com.example.factviewer.ui.MainActivity
import com.example.factviewer.ui.factdetailsfragment.FactDetailsFragment
import com.example.factviewer.ui.presenters.FactsListPresenter
import com.example.factviewer.ui.presenters.ItemSelectorPresenter
import com.example.factviewer.ui.presenters.LikePresenter
import com.example.factviewer.ui.views.FactListView
import com.example.factviewer.ui.views.ItemSelectorView
import com.example.factviewer.ui.views.LikeView
import kotlinx.android.synthetic.main.fact_list_fragment.*
import kotlinx.android.synthetic.main.fact_list_fragment.view.*

class FactsListFragment(private val animal: String) : MvpAppCompatFragment(), FactListView, ItemSelectorView, LikeView {

    private var previousAnimal = ""

    private val adapter = FactListAdapter()

    @InjectPresenter
    lateinit var factsListPresenter: FactsListPresenter

    @InjectPresenter
    lateinit var itemSelectorPresenter: ItemSelectorPresenter

    @InjectPresenter
    lateinit var likePresenter: LikePresenter

    override fun onResume() {
        super.onResume()
        if (previousAnimal != animal) {
            factsListPresenter.setAnimal(animal)
            factsListPresenter.loadData()
        }
        previousAnimal = animal
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fact_list_fragment, container, false)
        adapter.setOnClickListener(itemSelectorPresenter::onListItemSelected)
        adapter.setLikeClicker(likePresenter::updateLike)
        view.listRecyclerView.adapter = adapter
        return view
    }

    override fun onLikeClick(isLiked: Boolean) {
    }

    override fun showError() {
    }

    override fun hideError() {
    }

    override fun onStartLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onFinishLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onItemSelected(position: Int, id: String) {
        FragmentUtils.replaceFragment(activity as MainActivity, FactDetailsFragment(id), R.id.mainFrame, true)

    }

    override fun addFactsList(list: List<AnimalFact>) {
        adapter.addFacts(list)
    }
}