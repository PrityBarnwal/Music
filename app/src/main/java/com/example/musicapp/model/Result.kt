package com.example.musicapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val collections: Collections,
    var index: ArrayList<Index> = arrayListOf()
): Parcelable