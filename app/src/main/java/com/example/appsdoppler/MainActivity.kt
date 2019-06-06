package com.example.appsdoppler

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.example.appsdoppler.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    public lateinit var playing : String
    private lateinit var tablayout : TabLayout
    private lateinit var appbar : AppBarLayout
    private lateinit var viewpager : ViewPager


    var folder = Environment.getExternalStorageDirectory().path + "/Doppler/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tablayout = findViewById(R.id.tablayout_id)
        appbar = findViewById(R.id.appbarid)
        viewpager = findViewById(R.id.viewpager_id)

        var adapter = MyPageAdapter(this, supportFragmentManager)
//        var songs = File(folder).list()
//        var songarr = ArrayList<Song>()
////        for (i in songs.indices){
////            var s = songs.get(i)
//            songarr.add(Song(s, 20))
//        }

//        var adp = MyAdapter(songarr)


        //  instanciando y creando fragments
        adapter.addFragment(MisCancionesFragment(), "Canciones")
        adapter.addFragment(RecordFragment(), "Grabar")
        ///

        viewpager.adapter = adapter
        tablayout.setupWithViewPager(viewpager)



    }

}