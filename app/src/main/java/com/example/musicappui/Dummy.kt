package com.example.musicappui

import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon: Int, val name: String)

val library = listOf<Lib> (
    Lib(icon = R.drawable.baseline_queue_music_24, name = "Playlist"),
    Lib(icon = R.drawable.baseline_mic_external_on_24, name = "Artist"),
    Lib(icon = R.drawable.baseline_album_24, name = "Album"),
    Lib(icon = R.drawable.baseline_music_note_24, name = "Song"),
    Lib(icon = R.drawable.baseline_generating_tokens_24, name = "Genre")
    )