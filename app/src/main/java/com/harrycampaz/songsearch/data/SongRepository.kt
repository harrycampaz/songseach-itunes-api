package com.harrycampaz.songsearch.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.harrycampaz.songsearch.data.local.SongLocalData
import com.harrycampaz.songsearch.data.remote.SongDataSourceFactory
import com.harrycampaz.songsearch.song.domain.model.Result

class SongRepository(val context: Context) {


    fun getSongs(query: String): LivePagedListBuilder<Int, Result> {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isMetered: NetworkInfo? = cm.activeNetworkInfo

        val isConnect:Boolean = isMetered?.isConnectedOrConnecting == true

        return if(isConnect){

            searchQueryRemote(query, context)

        }else {

            searchQueryLocal(query, context)
        }

    }


    private fun searchQueryRemote(query: String, context: Context): LivePagedListBuilder<Int, Result>{
        val songDataSourceFactory =
            SongDataSourceFactory(
                query,
                context
            )

        val config= PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(20)
            .build()
        return LivePagedListBuilder(songDataSourceFactory, config)

    }

    private fun searchQueryLocal(query: String, context: Context): LivePagedListBuilder<Int, Result> {

        val songLocalData = SongLocalData(context)

        val config= PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(20)
            .build()

        return LivePagedListBuilder(songLocalData.getSongs(query), config)
    }
}