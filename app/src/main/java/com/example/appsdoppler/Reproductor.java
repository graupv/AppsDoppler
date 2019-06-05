package com.example.appsdoppler;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Reproductor extends AppCompatActivity {

    Button play_pause, btn_repetir;
    MediaPlayer mp;
    int repetir = 2, posicion = 0;

    MediaPlayer vectormp [] = new MediaPlayer[3];



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reproductor);

        play_pause = (Button)findViewById(R.id.btn_play);
        btn_repetir = (Button) findViewById(R.id.btn_shuffle);
    }

    public void PlayPause(View view){
        if(vectormp[posicion].isPlaying()){
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }else{
            vectormp[posicion].start();
            play_pause.setBackgroundResource((R.drawable.pausa));
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }
    }

    public void Stop(View v){
        if(vectormp[posicion] != null){
            vectormp[posicion].stop();

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
