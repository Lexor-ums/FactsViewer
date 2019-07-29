package com.example.calculator.utils.navigation.fragmentrouter

import androidx.fragment.app.Fragment
import com.example.factviewer.presentation.factdetailsfragment.FactDetailsFragment
import com.example.factviewer.presentation.factslistfragment.FactsListFragment

object Screens {

    const val BUNDLE_KEY = "bundleKey"

    enum class FRAGMENTS(fragmentName: String) {
        FACT_LIST_FRAGMENT("FACT_LIST_FRAGMENT"),
        DETAILS_FRAGMENT("DETAILS_FRAGMENT"),

    }

    fun createFragment(fragment: FRAGMENTS, arg  : String): Fragment = when (fragment) {
        Screens.FRAGMENTS.FACT_LIST_FRAGMENT -> FactsListFragment(arg)
        Screens.FRAGMENTS.DETAILS_FRAGMENT -> FactDetailsFragment(arg)
    }

}