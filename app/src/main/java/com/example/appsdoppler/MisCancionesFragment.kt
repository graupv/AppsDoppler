package com.example.appsdoppler

import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View

//import android.widget.
import android.view.ViewGroup

import kotlinx.android.synthetic.main.tab1_fragment_mis_canciones.*

class MisCancionesFragment : Fragment(){
    var folder = Environment.getExternalStorageDirectory().path + "/Doppler/"
//    private var lateinit rv: Recycler
    private lateinit var adapter : SongAddapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab1_fragment_mis_canciones, container, false)
    }



}