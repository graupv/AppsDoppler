package com.example.appsdoppler

import android.media.MediaRecorder
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
//import android.widget.
import android.view.ViewGroup
import kotlinx.android.synthetic.main.tab1_fragment_mis_canciones.*

class RecordFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.grabadora, container, false)
        return view
    }

}