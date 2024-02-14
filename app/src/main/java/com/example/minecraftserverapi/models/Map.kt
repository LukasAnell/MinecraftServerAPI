package com.example.minecraftserverapi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Map(
    var raw: String,
    var clean: String,
    var html: String
): Parcelable
