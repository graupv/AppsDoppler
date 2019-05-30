package com.example.appsdoppler

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class SongAddapter(val context: Context, val songs: List<SongModel>) : RecyclerView.Adapter<SongAddapter.SongHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SongHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, p0, false)
        return SongHolder(view)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(songHolder: SongHolder, pos: Int) {
        val song = songs[pos]
        songHolder.setData(song)
    }

    inner class SongHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(songModel: SongModel?){
            itemView.txvTitle.text = songModel!!.title

        }

    }
}