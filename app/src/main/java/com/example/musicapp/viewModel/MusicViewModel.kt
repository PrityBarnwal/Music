package com.example.musicapp.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.musicapp.MusicApplication
import com.example.musicapp.doNothing
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.repository.MusicRepository
import com.example.musicapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(app: Application, val musicRepo: MusicRepository) :
    AndroidViewModel(app) {
    var musicResponse: MusicResponse? = null
    val musicInfo: MutableLiveData<Resource<MusicResponse>> = MutableLiveData()

    fun getMusicInfo() = viewModelScope.launch {

        musicInfo.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = musicRepo.getMusicInfo()
                musicInfo.postValue(handleMusicResponse(response))
            } else {
                musicInfo.postValue(Resource.Error("No Internet Connection"))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> musicInfo.postValue(Resource.Error("Network Failure"))
                else -> musicInfo.postValue(Resource.Error(t.localizedMessage))
            }
        }
    }

    private fun handleMusicResponse(response: Response<MusicResponse>): Resource<MusicResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (musicResponse == null) {
                    musicResponse = resultResponse
                } else {
                    doNothing()
                }
                return Resource.Success(musicResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MusicApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activityNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activityNetwork) ?: return false

            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}