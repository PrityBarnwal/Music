package com.example.musicapp

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.adapter.BottomSheetAdapter
import com.example.musicapp.adapter.FilterAdapter
import com.example.musicapp.adapter.RecentlyWatchedAdapter
import com.example.musicapp.databinding.ActivityRecentlyWatchedBinding
import com.example.musicapp.databinding.BottomSheetDialogBinding
import com.example.musicapp.model.Index
import com.example.musicapp.sataticeModelData.FilterResponseData
import com.example.musicapp.utils.SaveDataUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentlyWatchedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentlyWatchedBinding
    private var positionSelected = -1
    private val recentlyWatchedAdapter by lazy {
        RecentlyWatchedAdapter()
    }
    val bottomSheetAdapter by lazy {
        BottomSheetAdapter()
    }

    val filterAdapter by lazy {
        FilterAdapter(positionSelected)
    }
    val itemIndex = SaveDataUtils.saveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentlyWatchedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (itemIndex != null) {
            recentlyWatchedAdapter.resultData = itemIndex
            binding.recMusicList.adapter = recentlyWatchedAdapter
            recentlyWatchedAdapter.addItems(itemIndex)
        }

        binding.apply {
            ivBack.setOnClickListener {
                onBackPressed()
            }
            recFilter.adapter = filterAdapter
            var count = 0
            itemIndex.forEach {
                if (count <= 5)
                filterAdapter.resultData.add(it)
                count++
            }

            filterAdapter.listener = { view, item, position ->
                openDialog(position,item)
            }
        }
    }


    private fun openDialog(position: Int,item:Index) {
        val dialog = BottomSheetDialog(this)
        val binding = BottomSheetDialogBinding.inflate(LayoutInflater.from(this))
        if (itemIndex != null) {
            binding.recEachItem.adapter = bottomSheetAdapter
            when (position) {
                0 -> {
                    binding.tvEducator.text ="Only Show Owned"
                    bottomSheetAdapter.data.clear()
                    bottomSheetAdapter.data.add("Yes")
                    bottomSheetAdapter.data.add("No")
                }
                1 -> {
                    binding.tvEducator.text ="Skill"
                    bottomSheetAdapter.data.clear()
                    bottomSheetAdapter.data.addAll(item.skill_tags)
                }
                2 -> {
                    binding.tvEducator.text ="Cirriculum"
                    bottomSheetAdapter.data.clear()
                    bottomSheetAdapter.data.addAll(item.curriculum_tags)
                }
                3 -> {
                    binding.tvEducator.text ="Style"
                    bottomSheetAdapter.data.clear()
                    bottomSheetAdapter.data.addAll(item.style_tags)
                }
                4 ->{
                    binding.tvEducator.text ="Educator"
                    bottomSheetAdapter.data.clear()
                    bottomSheetAdapter.data.add(item.educator)
                }
                5 ->{
                    binding.tvEducator.text ="Series"
                    bottomSheetAdapter.data.clear()
                    bottomSheetAdapter.data.addAll(item.series_tags)
                }
            }
        }

        binding.tvCancel.setOnClickListener { dialog.dismiss() }
        dialog.setContentView(binding.root)
        dialog.show()
        dialog.setCancelable(true)
    }

}
