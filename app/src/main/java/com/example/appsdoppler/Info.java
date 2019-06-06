package com.example.appsdoppler;

import android.os.Environment;

import java.util.ArrayList;

public class Info {
    String folder = Environment.getExternalStorageDirectory().getPath() + "/Doppler/";
    private static final Info ourInstance = new Info();
    Song song;
    String songPath;
    ArrayList<Song> songList;
    MyAdapter Ma;
    int pos;
    public static Info getInstance() {
        return ourInstance;
    }

    private Info() {
    }
    public void setPos(int n){
        pos = n;
    }

    public int getPos() {
        return pos;
    }

    public void setSong(Song song) {
        this.song = song;
    }
    public void setSongPath(String path) {
        this.songPath = path;
    }

    public Song getSong() {
        return song;
    }

    public String getSongPath(){
        System.out.println(folder + song.getName());
        return folder + song.getName();
    }

    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public void setMa(MyAdapter ma) {
        Ma = ma;
    }

    public MyAdapter getMa() {
        return Ma;
    }

    public void updateAdapter(){
        if (Ma != null){
            Ma.notifyDataSetChanged();
            System.out.println("called update adapter");

        }

    }
}
