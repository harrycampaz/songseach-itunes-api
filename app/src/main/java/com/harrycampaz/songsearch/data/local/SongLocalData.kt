package com.harrycampaz.songsearch.data.local

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.harrycampaz.songsearch.song.domain.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SongLocalData(context: Context): CoroutineScope {

    var songDao: SongDao

    init {
        val db = DatabaseHelper.getInstance(context)
        songDao = db.songDao()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    fun getSongs(query: String): DataSource.Factory<Int, Result>{
        return  songDao.getSongs("%$query%")
    }


    fun addSong(song: Result){
        launch { setSong(song) }
    }

    private suspend fun setSong(song: Result){
        withContext(Dispatchers.IO){
            songDao.insert(song)

        }
    }

}