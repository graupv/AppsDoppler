package com.example.appsdoppler

import android.media.MediaPlayer
import android.media.MediaRecorder
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
import android.widget.Button
import android.widget.Toast
import com.example.appsdoppler.R.id.btn_rec
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.tab1_fragment_mis_canciones.*
import java.io.File
import java.io.IOException

class RecordFragment : Fragment(), MediaPlayer.OnCompletionListener{
    var folder = Environment.getExternalStorageDirectory().path + "/Doppler/"
    var n = File(folder).list().size
    override fun onCompletion(mp: MediaPlayer?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    lateinit var btn_rec: Button
    lateinit var btn_rep: Button
    private lateinit var med_rec: MediaRecorder
    private lateinit var filename: String
    private lateinit var file: File

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.grabadora, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        directory()
        //  revisa si esta el directorio donde se almacena las grabaciones
        super.onViewCreated(view, savedInstanceState)
        btn_rec = view.findViewById<Button>(R.id.btn_rec)
        btn_rep = view.findViewById<Button>(R.id.btn_play)

        btn_rec.setOnClickListener {
            Recorder(it)
        }
    }

    fun Recorder(v: View) {
        med_rec = MediaRecorder()


        filename = folder + "cancion" + (n + 1).toString() + ".3gp"
        if (File(filename).exists()){
            println("found: " + filename)
        }
        file = File(filename)
        med_rec.setAudioSource(MediaRecorder.AudioSource.MIC)
        med_rec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        med_rec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        try {
            file = File.createTempFile("temporary", ".3gp", file)
        } catch (e: IOException) {
        }

        med_rec.setOutputFile(file.absolutePath)
        try {
            med_rec.prepare()
        } catch (e: IOException) {
        }

        med_rec.start()
        Toast.makeText(context, "Grabando", Toast.LENGTH_SHORT).show()
        //  re bind para stop
        btn_rec.setOnClickListener {
            println("****rebind to stop")
            stopRelease()
            println(File(folder).list().size)
            n++
        }
    }

        fun stopRelease(){
            med_rec.stop()
            Toast.makeText(context, "Fin de Grabacion", Toast.LENGTH_SHORT).show()
            println(filename)
            med_rec.release()
            var med_rec: MediaRecorder? = null
            //  rebind para volver a grabar
            btn_rec.setOnClickListener {
                println("****rebind to record")
                Recorder(it)
            }
        }

    fun directory(){
        val f = File(folder)
        if (!f.exists()){
            try {
                f.mkdir()
                println("*****folder directory created")
            } catch (e: Exception){
                println("*****Unable to create directory")
                e.printStackTrace()
                }
            } else {
            println("***** Folder exists")
            println("***HAS: " + n.toString() + " recordings")
        }
        }

    }




