package com.randalvance.catchat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val inboxFragment = InboxFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content_frame, inboxFragment)
        transaction.commit()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        var fragment : Fragment? = null
        var intent : Intent? = null

        when (id) {
            R.id.nav_drafts -> fragment = DraftsFragment()
            R.id.nav_sent -> fragment = SentItemsFragment()
            R.id.nav_trash -> fragment = TrashFragment()
            R.id.nav_help -> intent = Intent(this, HelpActivity::class.java)
            R.id.nav_feedback -> intent = Intent(this, FeedbackActivity::class.java)
            else -> fragment = InboxFragment()
        }

        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_frame, fragment)
            ft.commit()
        } else {
            startActivity(intent)
        }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}
