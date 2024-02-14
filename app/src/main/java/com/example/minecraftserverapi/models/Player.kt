package com.example.minecraftserverapi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    var name: String,
    var uuid: String
): Parcelable
