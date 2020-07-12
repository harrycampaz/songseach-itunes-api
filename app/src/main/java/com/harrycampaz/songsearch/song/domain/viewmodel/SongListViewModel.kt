package com.harrycampaz.songsearch.song.domain.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.harrycampaz.songsearch.data.SongRepository
import com.harrycampaz.songsearch.song.domain.model.Result

class SongListViewModel : ViewModel(){

    var songsPageList: LiveData<PagedList<Result>> = MutableLiveData()

    var isLoading = MutableLiveData<Boolean>()

    var resultNotFound = MutableLiveData<Boolean>()

    lateinit var songRepository: SongRepository

    fun setup(context: Context){
        songRepository = SongRepository(context)
    }

    fun searchQuery(query: String) {

        songsPageList = songRepository.getSongs(query).setBoundaryCallback(object: PagedList.BoundaryCallback<Result>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()

           super.onZeroItemsLoaded()

                isResultNotFound()
                isProcessFinished()
            }

            override fun onItemAtFrontLoaded(itemAtFront: Result) {
                super.onItemAtFrontLoaded(itemAtFront)
                isProcessFinished()
            }
        }).build()
    }


    fun isProcessFinished() {
        isLoading.value = true
    }

    fun isResultNotFound() {
        resultNotFound.value = true
    }

}