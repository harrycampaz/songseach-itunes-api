package com.harrycampaz.songsearch.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.harrycampaz.songsearch.data.RetrofitInstance
import com.harrycampaz.songsearch.data.remote.DataServices
import com.harrycampaz.songsearch.data.remote.SongDataSource
import com.harrycampaz.songsearch.song.domain.model.Result


class SongDataSourceFactory(val query: String): DataSource.Factory<Int, Result>() {

    val songLiveDataSource = MutableLiveData<SongDataSource>()
    val dataServices: DataServices = RetrofitInstance.getDataService()

    override fun create(): DataSource<Int, Result> {
        val userDataSource =
            SongDataSource(dataServices, query)
            songLiveDataSource.postValue(userDataSource)

        return  userDataSource
    }
}