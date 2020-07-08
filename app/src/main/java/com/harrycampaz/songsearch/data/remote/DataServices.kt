package com.harrycampaz.songsearch.data.remote

import com.harrycampaz.songsearch.data.remote.restapi.Endpoints
import com.harrycampaz.songsearch.song.domain.model.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataServices {

    @GET(Endpoints.GET_SEARCH)
    fun getSongs(@Query("term") term: String,
                 @Query("mediaType") mediaType: String = "music",
                 @Query("offset") offset: Int? = null,
                 @Query("limit") limit: Int
    ): Call<Results>
}