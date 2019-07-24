package com.example.calculator.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.factviewer.ui.factslistfragment.FactsListFragment

class FragmentUtils {
    companion object {
        fun replaceFragment(activity: AppCompatActivity, fragment: Fragment, containerId: Int, needBackStack: Boolean) {
            val fragmentManager = activity.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            if (needBackStack)
                transaction.addToBackStack(fragment.javaClass.canonicalName)
            transaction.replace(containerId, fragment, fragment.javaClass.canonicalName)
            transaction.commit()
        }
    }
}