package com.example.musicapp.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.repository.MusicRepository

class MusicViewModelProviderFactory(
    val app: Application,
    val musicRepository: MusicRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MusicViewModel(app,musicRepository) as T
    }

}