package com.harrycampaz.songsearch.song.domain.model

import java.io.Serializable

class Result: Serializable {
    var artistId: Int =0
    lateinit var artistName: String
    lateinit var artistViewUrl: String
    lateinit var artworkUrl100: String
    var collectionId: Int = 0
    var collectionName: String = ""
    lateinit var collectionViewUrl: String
     var discCount: Int =0
     var discNumber: Int = 0
    lateinit var longDescription: String
    lateinit var previewUrl: String
    lateinit var primaryGenreName: String
    lateinit var releaseDate: String
    lateinit var shortDescription: String
    lateinit var trackCensoredName: String
     var trackCount: Int = 0
    lateinit var trackExplicitness: String
     var trackHdPrice: Double = 0.0
    var trackId: Long = 0
     var trackName: String = ""
     var trackViewUrl: String = ""
     var wrapperType: String = ""
}