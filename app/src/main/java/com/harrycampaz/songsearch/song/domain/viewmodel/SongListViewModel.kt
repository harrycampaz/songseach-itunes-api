package com.harrycampaz.songsearch.song.domain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.harrycampaz.songsearch.data.SongDataSourceFactory
import com.harrycampaz.songsearch.song.domain.model.Result
import java.util.concurrent.Executor
import java.util.concurrent.Executors

private const val TAG = "SongListViewModel"
class SongListViewModel : ViewModel(){

    var songsPageList: LiveData<PagedList<Result>> = MutableLiveData()

    var isLoading = MutableLiveData<Boolean>()

    var resultNotFound = MutableLiveData<Boolean>()

    fun refresh(query: String){
        val songDataSourceFactory = SongDataSourceFactory(query)

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

    fun isProcessFinished() {
        isLoading.value = true
    }

    fun isResultNotFound() {
        resultNotFound.value = true
    }

}