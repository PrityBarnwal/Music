package com.example.musicapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Index(
    val authorid: Int,
    val cd_downloads: Int,
    val curriculum_tags: ArrayList<String>,
    val downloadid: Int,
    val educator: String,
    val id: Int,
    val owned: Int,
    val progress_tracking: Double,
    val release_date: String,
    val sale: Int,
    val series_tags: ArrayList<String>,
    val skill_tags: ArrayList<String>,
    val status: Int,
    val style_tags: ArrayList<String>,
    var title: String,
    val video_count: Int,
    val watched: Int
):Parcelable