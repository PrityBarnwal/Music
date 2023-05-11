package com.example.musicapp.api

import com.example.musicapp.model.MusicResponse
import com.example.musicapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface MusicApi {

    @GET(Constants.API_ID)
    suspend fun getMusicInfo(
    ) : Response<MusicResponse>
}