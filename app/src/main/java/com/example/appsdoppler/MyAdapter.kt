package com.example.appsdoppler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyAdapter(val songList: ArrayList<Song>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.bindItems(songList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return songList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: Song) {
            val textSongName = itemView.findViewById(R.id.songName) as TextView
            val textSongDuration  = itemView.findViewById(R.id.songDuration) as TextView
            textSongName.text = user.name
            textSongDuration.text = user.dur.toString()
        }
    }
}