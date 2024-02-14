package com.example.minecraftserverapi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plugin(
    var name: String,
    var version: String
): Parcelable
