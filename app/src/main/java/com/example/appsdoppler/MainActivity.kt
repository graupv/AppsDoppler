package com.example.appsdoppler

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.appsdoppler.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tablayout : TabLayout
    private lateinit var appbar : AppBarLayout
    private lateinit var viewpager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tablayout = findViewById(R.id.tablayout_id)
        appbar = findViewById(R.id.appbarid)
        viewpager = findViewById(R.id.viewpager_id)
        var adapter = MyPageAdapter(this, supportFragmentManager)

        //  instanciando y creando fragments
        adapter.addFragment(MisCancionesFragment(), "Canciones")
        adapter.addFragment(RecordFragment(), "Grabar")
        ///

        viewpager.adapter = adapter
        tablayout.setupWithViewPager(viewpager)


    }

}