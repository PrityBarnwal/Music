package com.example.musicapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Smart(
    val courses: List<Int>,
    val description: String,
    val id: String,
    val is_archive: Int,
    val is_default: Int,
    val label: String,
    val smart: String
):Parcelable