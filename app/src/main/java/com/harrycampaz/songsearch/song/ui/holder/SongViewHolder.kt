package com.harrycampaz.songsearch.song.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harrycampaz.songsearch.song.domain.model.Result
import kotlinx.android.synthetic.main.item_song.view.*


class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val imageView = itemView.im_avatar
    private val tvSong = itemView.tv_song
    private val tvAccess = itemView.tv_access_id


    fun bind(song: Result) {
        tvSong.text = song.artistName
        tvAccess.text = song.trackName

        Glide.with(imageView.context)
            .load(song.artworkUrl100)
            .into(imageView)
    }

}