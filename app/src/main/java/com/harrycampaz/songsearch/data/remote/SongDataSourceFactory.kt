package com.harrycampaz.songsearch.data.remote

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.harrycampaz.songsearch.song.domain.model.Result


class SongDataSourceFactory(val query: String, val context: Context): DataSource.Factory<Int, Result>() {

    val songLiveDataSource = MutableLiveData<SongDataSource>()
    val dataServices: DataServices =
        RetrofitInstance.getDataService()

    override fun create(): DataSource<Int, Result> {
        val userDataSource =
            SongDataSource(dataServices, query, context)
            songLiveDataSource.postValue(userDataSource)

        return  userDataSource
    }
}