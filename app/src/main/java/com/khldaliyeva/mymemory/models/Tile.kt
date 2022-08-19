package com.khldaliyeva.mymemory.models

data class Tile(
    val identifier: Int,
    var isFlipped: Boolean = false,
    var isMatched: Boolean = false
)
