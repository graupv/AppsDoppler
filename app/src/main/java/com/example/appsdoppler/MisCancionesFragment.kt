package com.example.appsdoppler

import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

//import android.widget.
import android.view.ViewGroup
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.tab1_fragment_mis_canciones.*
import java.io.File

class MisCancionesFragment : Fragment(){
    private var recyclerView : RecyclerView? = null
    private lateinit var adp : MyAdapter
    var folder = Environment.getExternalStorageDirectory().path + "/Doppler/"
//    private var lateinit rv: Recycler

    public override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var songs = File(folder).list()
        var songarr = ArrayList<Song>()
        for (i in songs.indices){
            var s = songs.get(i)
            songarr.add(Song(s, 20))
            println(i)
        }
        adp = MyAdapter(songarr)

        return inflater.inflate(R.layout.tab1_fragment_mis_canciones, container, false)
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view?.findViewById(R.id.rv) as? RecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recyclerView?.adapter = adp
//        recyclerView?.nested
        adp.notifyDataSetChanged()


    }




}