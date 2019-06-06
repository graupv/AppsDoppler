package com.example.appsdoppler;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.*;
import java.net.URI;

public class Reproductor extends AppCompatActivity {

    String folder = Environment.getExternalStorageDirectory().getPath()+ "/Doppler/";
    Button play_pause, btn_repetir;
    MediaPlayer mp;
    int repetir = 2, posicion = 0;
    File[] songs;
    Info infos;

    MediaPlayer vectormp [] = new MediaPlayer[999];

    void doThing(){
        //  popular lista
        File f = new File(folder);
        songs = f.listFiles();
        if (songs.length > 0){
//            System.out.println(songs[0]);
            System.out.println("Tenemos: " + songs.length + " grabaciones.");
        }
    }
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reproductor);
        doThing();
        infos = Info.getInstance();
        play_pause = (Button)findViewById(R.id.btn_play);
        btn_repetir = (Button) findViewById(R.id.btn_shuffle);
//
        for (int i = 0; i < songs.length; i++) {
        uri = Uri.parse(songs[i].getAbsolutePath());

            vectormp[i] = MediaPlayer.create(this, uri);
        }

    }

    public void PlayPause(View view){
        if(vectormp[infos.getPos()].isPlaying()){
            vectormp[infos.getPos()].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }else{
//            vectormp[posicion] = MediaPlayer.create(this, uri.parse(inf.songPath));
            vectormp[infos.getPos()].start();
            play_pause.setBackgroundResource((R.drawable.pausa));
            Toast.makeText(this, songs[infos.getPos()].getName(), Toast.LENGTH_SHORT).show();
        }
    }

    public void Stop(View v){
        if(vectormp[infos.getPos()] != null){
            vectormp[infos.getPos()].stop();

            posicion = 0;
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }

    public void Repetir(View view){
        if(repetir == 1){
            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(false);
            repetir = 2;
        }else{
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir = 1;
        }
    }

    public void Siguiente(View v){
        if(posicion < vectormp.length -1){
            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                posicion++;
                vectormp[posicion].start();
            } else{
                posicion++;
            }
        }else{
            Toast.makeText(this, "No hay más canciones.", Toast.LENGTH_SHORT).show();
        }
    }

    public void Anterior(View v){
        if (posicion >= 1) {
            if (vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                posicion--;
                vectormp[posicion].start();
            }else {
                posicion--;
            }
        } else {
            Toast.makeText(this, "No hay más canciones.", Toast.LENGTH_SHORT).show();
        }
    }
}
