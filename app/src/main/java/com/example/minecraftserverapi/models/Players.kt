package com.example.minecraftserverapi.models

data class Players(
    var online: Int,
    var max: Int,
    var list: List<Player>
)
