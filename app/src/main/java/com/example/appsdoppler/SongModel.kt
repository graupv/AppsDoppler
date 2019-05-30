package com.example.appsdoppler

data class SongModel (var title: String, var duration: Int)

object SongList{
    val songs = listOf<SongModel>(
        SongModel("Test 01", 80),
        SongModel("Test 02", 80),
        SongModel("Test 03", 80),
        SongModel("Test 04", 80),
        SongModel("Test 05", 80),
        SongModel("Test 06", 80),
        SongModel("Test 07", 80)
    )
}