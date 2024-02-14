package com.example.minecraftserverapi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Debug(
    var ping: Boolean,
    var query: Boolean,
    var srv: Boolean,
    var querymismatch: Boolean,
    var ipinsrv: Boolean,
    var cnameinsrv: Boolean,
    var animatedmotd: Boolean,
    var cachehit: Boolean,
    var cachetime: Long,
    var cacheexpire: Long,
    var apiversion: Int
): Parcelable
