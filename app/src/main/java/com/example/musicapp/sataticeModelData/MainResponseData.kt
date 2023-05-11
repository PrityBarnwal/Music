package com.example.musicapp.sataticeModelData

data class MainResponseData(
    val name:String,
    val listItem:ArrayList<MainChildResponseData>,
)

data class MainChildResponseData(
    val image:Int,
    val name:String,
    val composer:String,
)