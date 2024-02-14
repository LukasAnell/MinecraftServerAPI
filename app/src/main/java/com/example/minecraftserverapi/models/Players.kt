package com.example.minecraftserverapi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Players(
    var online: Int,
    var max: Int,
    var list: List<Player>? = null
): Parcelable
