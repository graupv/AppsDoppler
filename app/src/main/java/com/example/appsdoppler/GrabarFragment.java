package com.example.appsdoppler;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GrabarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GrabarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GrabarFragment extends Fragment {

    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private Button btn_recorder;
    private  Button btn_player;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GrabarFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment GrabarFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static GrabarFragment newInstance(String param1, String param2) {
//        GrabarFragment fragment = new GrabarFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        btn_recorder = (Button) btn_recorder.findViewById(R.id.btn_rec);
        btn_recorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onCreate onclick for recorder");
                Recorder(v);
            }
        });

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }

        btn_recorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grabacion == null){
                    archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
                    grabacion = new MediaRecorder();
                    grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
                    grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                    grabacion.setOutputFile(archivoSalida);

                    try{
                        grabacion.prepare();
                        grabacion.start();
                    } catch(IOException e){
                    }

                    btn_recorder.setBackgroundResource(R.drawable.rec);
                    Toast.makeText(getContext(), "Grabando...", Toast.LENGTH_SHORT).show();

                }else if(grabacion != null){

                    grabacion.stop();
                    grabacion.release();
                    grabacion = null;
                    btn_recorder.setBackgroundResource(R.drawable.stop_rec);
                    Toast.makeText(getContext(), "Grabación finalizada.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_player = (Button) btn_player.findViewById(R.id.btn_play);
        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("set onclick reproducir, onCreate");
                reproducir(v);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        btn_player = (Button) btn_player.findViewById(R.id.btn_play);
        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("set onclick reproducir in oncreateview");
                reproducir(v);

            }
        });

        btn_recorder = (Button) btn_recorder.findViewById(R.id.btn_rec);
        btn_recorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recorder(v);
            }
        });
        return inflater.inflate(R.layout.grabadora, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " mustfaf awe implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    public void Recorder(View v){
        System.out.println("recorder");
        if(grabacion == null){
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            grabacion = new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            grabacion.setOutputFile(archivoSalida);

            try{
                grabacion.prepare();
                grabacion.start();
            } catch(IOException e){
                e.printStackTrace();
            }

            btn_recorder.setBackgroundResource(R.drawable.rec);
            Toast.makeText(getContext(), "Grabando...", Toast.LENGTH_SHORT).show();

        }else if(grabacion != null){

            grabacion.stop();
            grabacion.release();
            grabacion = null;
            btn_recorder.setBackgroundResource(R.drawable.stop_rec);
            Toast.makeText(getContext(), "Grabación finalizada.", Toast.LENGTH_SHORT).show();

        }
    }

    public void reproducir(View view){
        System.out.println("reproducir");
        MediaPlayer mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
        }catch(IOException e){
            e.printStackTrace();
        }

        mediaPlayer.start();
        Toast.makeText(getContext(), "Reproduciendo audio.", Toast.LENGTH_SHORT).show();

    }
}
