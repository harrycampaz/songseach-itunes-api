package com.harrycampaz.songsearch.data.remote



import com.harrycampaz.songsearch.data.remote.restapi.Endpoints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun getDataService() : DataServices {

        return Retrofit.Builder()
            .baseUrl(Endpoints.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataServices::class.java)

    }
}