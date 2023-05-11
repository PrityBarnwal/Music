package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.adapter.MainAdapter
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.repository.MusicRepository
import com.example.musicapp.utils.Resource
import com.example.musicapp.utils.SaveDataUtils
import com.example.musicapp.viewModel.MusicViewModel
import com.example.musicapp.viewModel.MusicViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MusicViewModel

    private val mainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = MusicRepository()

        val viewModelProviderFactory = MusicViewModelProviderFactory(application, repository)


        mainViewModel =
            ViewModelProvider(this, viewModelProviderFactory)[MusicViewModel::class.java]

        mainViewModel.getMusicInfo()
        mainViewModel.musicInfo.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    response.data?.let { musicResponse ->
                        binding.recMainScreen.adapter = mainAdapter
                        mainAdapter.addItems(musicResponse.record.result)
                    }
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    response.message?.let { message ->
                        Log.d("message", "An Error Occurred: $message")
                    }
                }

                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                else -> {
                    doNothing()
                }
            }
        })

        mainAdapter.listener = { view, item, position ->
            if (position == 1) {
                val intent = Intent(this@MainActivity, RecentlyWatchedActivity::class.java)
                startActivity(intent)
                SaveDataUtils.saveData = item.index


            }
        }
    }
}

fun doNothing() = Unit