package com.example.musicapp.repository

import com.example.musicapp.api.RetrofitInstance
import javax.inject.Inject

class MusicRepository @Inject constructor(){
    suspend fun getMusicInfo() =
        RetrofitInstance.api.getMusicInfo()
}