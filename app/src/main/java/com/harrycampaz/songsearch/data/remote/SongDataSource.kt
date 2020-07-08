package com.harrycampaz.songsearch.data.remote

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.harrycampaz.songsearch.song.domain.model.Results
import com.harrycampaz.songsearch.song.domain.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "SongDataSource"
class SongDataSource(var dataServices: DataServices, val query: String) : PageKeyedDataSource<Int, Result>() {

   //  val dataServices: DataServices = RetrofitInstance.getDataService()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {

      //  val dataServices: DataServices = RetrofitInstance.getDataService()

        val call: Call<Results> = dataServices.getSongs(term = query, limit = LIMIT)

        call.enqueue(object : Callback<Results> {
            override fun onFailure(call: Call<Results>, t: Throwable) {
                Log.e(TAG, "onFailure:", t)
            }
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if(response.isSuccessful) run {

                    Log.d(TAG, "onResponse: ${response.body()}")

                    val apiResponse = response.body()!!

                    val items = apiResponse.results

                    items.let {
                        callback.onResult(items, null, LIMIT)
                    }

                }
            }

        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        val call: Call<Results> = dataServices.getSongs(term = query, offset =  params.key + 20, limit = LIMIT)

        call.enqueue(object : Callback<Results> {
            override fun onFailure(call: Call<Results>, t: Throwable) {
                Log.e(TAG, "onFailure: Error en l consulta LoadAfter", t)
            }
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if(response.isSuccessful) run {
                    val apiResponse = response.body()!!

                    val items = apiResponse.results

                    items.let {
                        callback.onResult(items,  params.key + 20)
                    }
                    // callback.onResult(response.body()!!.results, params.key + 20)
                }
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

    }

    companion object {
        const val LIMIT = 20
    }
}