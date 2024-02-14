package com.example.minecraftserverapi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    var raw: List<String>? = null,
    var clean: List<String>? = null,
    var html: List<String>? = null
): Parcelable
