package com.harrycampaz.songsearch.song.ui.adapter


import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

import com.harrycampaz.songsearch.R
import  com.harrycampaz.songsearch.song.domain.model.Result
import com.harrycampaz.songsearch.song.domain.model.SongListener
import com.harrycampaz.songsearch.song.ui.holder.SongViewHolder

class SongAdapter(val songListener: SongListener) : PagedListAdapter<Result, SongViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false)

        return  SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = getItem(position)

        song?.let {
            holder.bind(song, songListener, position)
        }
    }


    class DiffUtilCallBack : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem

        }
    }

}