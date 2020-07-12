package com.harrycampaz.songsearch.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harrycampaz.songsearch.song.domain.model.Result

@Dao
interface SongDao  {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(song: Result)

    @Query("SELECT * FROM result_table where artistName LIKE  :name OR trackName LIKE :name order by trackName")
    fun getSongs(name: String?): DataSource.Factory<Int, Result>


}