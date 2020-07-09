package com.harrycampaz.songsearch.song.domain.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.harrycampaz.songsearch.data.SongDataSourceFactory
import com.harrycampaz.songsearch.data.local.SongLocalData
import com.harrycampaz.songsearch.song.domain.model.Result

private const val TAG = "SongListViewModel"
class SongListViewModel() : ViewModel(){

    var songsPageList: LiveData<PagedList<Result>> = MutableLiveData()

    var isLoading = MutableLiveData<Boolean>()

    var resultNotFound = MutableLiveData<Boolean>()

    fun searchQuery(query: String, context: Context) {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isMetered: NetworkInfo? = cm.activeNetworkInfo

        val isConnect:Boolean = isMetered?.isConnectedOrConnecting == true

        if(isConnect){

            searchQueryRemote(query, context)
        }else {

            searchQueryLocal(query, context)
        }

    }

    private fun searchQueryRemote(query: String, context: Context){
        val songDataSourceFactory = SongDataSourceFactory(query, context)

        val config= PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(20)
            .build()
        songsPageList = LivePagedListBuilder(songDataSourceFactory, config)
            .setBoundaryCallback(object: PagedList.BoundaryCallback<Result>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                    // Handle empty initial load here

                    isResultNotFound()
                    isProcessFinished()

                }

                override fun onItemAtFrontLoaded(itemAtFront: Result) {
                    super.onItemAtFrontLoaded(itemAtFront)
                    // Here you can listen to first item on list
                    isProcessFinished()
                }
            })
            .build()

    }

    private fun searchQueryLocal(query: String, context: Context){

        val songLocalData = SongLocalData(context)


        val config= PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(20)
            .build()

        songsPageList = LivePagedListBuilder(songLocalData.getSongs(query), config)
            .setBoundaryCallback(object: PagedList.BoundaryCallback<Result>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                    // Handle empty initial load here

                    isResultNotFound()
                    isProcessFinished()

                }

                override fun onItemAtFrontLoaded(itemAtFront: Result) {
                    super.onItemAtFrontLoaded(itemAtFront)
                    // Here you can listen to first item on list
                    isProcessFinished()
                }
            })
            .build()
    }

    fun isProcessFinished() {
        isLoading.value = true
    }

    fun isResultNotFound() {
        resultNotFound.value = true
    }

}