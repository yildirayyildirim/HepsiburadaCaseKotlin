package com.app.hepsiburada.model

data class TunesModel(
    val trackId: Int,
    val artworkUrl100: String,
    private val formattedPrice: String,
    private val collectionPrice: Double,
    val trackName: String,
    val releaseDate: String,
) {
    val price: String
        get() {
            return if (formattedPrice.isNullOrEmpty()) "Price: $$collectionPrice" else "Price: $formattedPrice"
        }
}