package com.example.minecraftserverapi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Protocol(
    var version: Int,
    var name: String
): Parcelable
