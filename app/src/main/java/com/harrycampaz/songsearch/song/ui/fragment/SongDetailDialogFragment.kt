package com.harrycampaz.songsearch.song.ui.fragment

import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.harrycampaz.songsearch.R
import com.harrycampaz.songsearch.song.domain.model.Result
import kotlinx.android.synthetic.main.fragment_song_detail_dialog.*

private const val TAG = "SongDetailDialogFragmen"
class SongDetailDialogFragment : DialogFragment() {

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar_song_dialog.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_close_24)

        toolbar_song_dialog.setTitleTextColor(Color.BLACK)
        val song = arguments?.getSerializable("song") as Result

         mediaPlayer = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(song.previewUrl)
            prepare() // might take long! (for buffering, etc)
        }

        toolbar_song_dialog.setNavigationOnClickListener {
            dismiss()
            mediaPlayer?.pause()
        }

        Glide.with(view.context)
            .load(song.artworkUrl100)
            .into(iv_detail_dialog_image)

        toolbar_song_dialog.title = song.trackName
        song.collectionName?.let {
            tv_details_dialog_name.text = it
        }
        tv_details_dialog_banda.text = song.artistName

        mediaPlayer?.let { media ->


            iv_play.setOnClickListener {

                if(media.isPlaying){
                    media.pause()
                    iv_
                }else {
                    media.start()
                }

            }

        }


    }



    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.let {
         it.pause()
        }
    }


}