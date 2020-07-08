package com.harrycampaz.songsearch.song.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harrycampaz.songsearch.R
import com.harrycampaz.songsearch.song.domain.viewmodel.SongListViewModel
import com.harrycampaz.songsearch.song.ui.adapter.SongAdapter
import kotlinx.android.synthetic.main.fragment_song_list.*
import kotlin.math.log

private const val TAG = "SongListFragment"
class SongListFragment : Fragment() {

    lateinit var adapter: SongAdapter

    lateinit var songListViewModel: SongListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query= arguments?.getString("query") as String

        showScreen(query)


    }

    private fun showScreen(query: String) {
        adapter = SongAdapter()
        shimmer_view_container.startShimmer()

        rv_song.layoutManager = LinearLayoutManager(context)

        songListViewModel = ViewModelProvider(this).get(SongListViewModel::class.java)

        query?.let {
            songListViewModel.refresh(it)
        }

        songListViewModel.songsPageList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)

        })

        songListViewModel.isLoading.observe(viewLifecycleOwner, Observer {

            if(it){
                shimmer_view_container.visibility = View.GONE
                shimmer_view_container.stopShimmer()
            }
        })

        songListViewModel.resultNotFound.observe(viewLifecycleOwner, Observer {
            if(it){
                iv_result_not_found.visibility = View.VISIBLE
                rv_song.visibility = View.INVISIBLE
            }
        })

        rv_song.adapter = adapter
    }

}