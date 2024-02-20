package com.example.minecraftserverapi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServerStatus(
    var online: Boolean,
    var ip: String? = "",
    var port: Int,
    var hostname: String? = "",
    var debug: Debug,
    var version: String? = "",
    var protocol: Protocol,
    var icon: String,
    var software: String? = "",
    var map: Map? = null,
    var gamemode: String? = "",
    var serverid: String? = "",
    var eula_blocked: Boolean,
    var motd: Motd,
    var players: Players,
    var plugins: List<Plugin>? = null,
    var mods: List<Mod>? = null,
    var info: Info? = null
): Parcelable
