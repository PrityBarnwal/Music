package com.example.musicapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collections(
//    val curated: List<Any> = listOf(),
    val smart: ArrayList<Smart> = arrayListOf(),
    val user: List<User> = listOf()
):Parcelable