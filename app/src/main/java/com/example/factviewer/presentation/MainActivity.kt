package com.example.factviewer.presentation

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.factviewer.MainApplication.Companion.fragmentRouter
import com.example.factviewer.R
import com.example.factviewer.presentation.common.ItemSelectorView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainActivityView, ItemSelectorView, NavigationView.OnNavigationItemSelectedListener{

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: androidx.drawerlayout.widget.DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = nav_view
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        fragmentRouter.initRouter(
            supportFragmentManager,
            R.id.mainFrame,
            ::finishActivity
        )
    }

    override fun onBackPressed() {
        val drawerLayout: androidx.drawerlayout.widget.DrawerLayout = drawer_layout
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_cats -> presenter.onListFragmentSelected("cat")
            R.id.nav_dogs -> presenter.onListFragmentSelected("dog")
            R.id.nav_nails -> presenter.onListFragmentSelected("snail")
            R.id.nav_horses -> presenter.onListFragmentSelected("horse")
            R.id.nav_chosen -> presenter.onListFragmentSelected("chosen")
        }
        val drawerLayout: androidx.drawerlayout.widget.DrawerLayout = drawer_layout
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun finishActivity() {
        finish()
    }

    override fun onItemSelected(position: Int, id: String) {
        presenter.onDetailsSelected(id)
    }
}
