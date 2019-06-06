package com.example.appsdoppler

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.File

class MyAdapter(val songList: ArrayList<Song>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var folder = Environment.getExternalStorageDirectory().path + "/Doppler/"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(v)
    }

    var infos : Info = Info.getInstance()
    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.bindItems(songList[position])

        val but : Button = holder.itemView.findViewById<Button>(R.id.elimButt)
        but.setOnClickListener {
            val file = File(folder + songList[position].name)
            println(folder + songList[position].name)
            println("deleting")

            songList.remove(songList[position])
            notifyItemRemoved(position)
            notifyDataSetChanged()
            file.delete()

        }
        holder.itemView.setOnClickListener {
            //  llamar al intent con la siguiente actividad por cancion
            //
            val context: Context = holder.itemView.context
            val intent = Intent(context, Reproductor::class.java)
            notifyDataSetChanged()
            infos.setPos(position)
            infos.ma = this.getInstance()
            infos.ma.notifyDataSetChanged()

            println("position is: " + position)
//            inf.setSongPath(folder + songList.get(position))
            startActivity(context, intent, intent.extras)
        }

    }

    public fun getInstance(): MyAdapter{
        return this
    }


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

        fun refresh(){

        }
    }
}