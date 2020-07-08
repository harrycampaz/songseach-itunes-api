package com.harrycampaz.songsearch.song.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.harrycampaz.songsearch.R
import kotlinx.android.synthetic.main.fragment_song_search_form.*

private const val TAG = "SongSearchFormFragment"

class SongSearchFormFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_search_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_query.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                p0?.let { query ->
                    if(query.length > 1){
                       btn_query.setOnClickListener {
                           et_query.text = null
                           val bundle = bundleOf("query" to query.toString())
                           Navigation.findNavController(it).navigate(R.id.action_songSearchFormFragment_to_songListFragment, bundle)
                       }
                    }else {

                        btn_query.setOnClickListener(null)
                    }
                }
            }

        })

    }
}