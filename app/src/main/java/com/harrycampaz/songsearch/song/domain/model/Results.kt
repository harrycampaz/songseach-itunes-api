package com.harrycampaz.songsearch.song.domain.model

import com.harrycampaz.songsearch.song.domain.model.Result

data class Results(
    val resultCount: Int,
    val results: List<Result>
)