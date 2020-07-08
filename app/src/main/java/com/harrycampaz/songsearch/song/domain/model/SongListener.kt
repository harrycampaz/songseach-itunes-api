package com.harrycampaz.songsearch.song.domain.model

interface SongListener {

    fun  onSongClicked(song: Result, position: Int)
}