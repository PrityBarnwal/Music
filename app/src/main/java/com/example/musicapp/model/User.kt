package com.example.musicapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val courses: List<Int>,
    val description: String,
    val id: Int,
    val is_archive: Int,
    val is_default: Int,
    val label: String
):Parcelable