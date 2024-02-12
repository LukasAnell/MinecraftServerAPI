package com.example.minecraftserverapi.models

data class ServerStatus(
    var online: Boolean,
    var ip: String,
    var port: Int,
    var hostname: String,
    var debug: Debug,
    var version: String,
    var protocol: Protocol,
    var icon: String,
    var software: String,
    var map: Map,
    var gamemode: String,
    var serverid: String,
    var eula_blocked: Boolean,
    var motd: Motd,
    var players: Players,
    var plugins: List<Plugin>,
    var mods: List<Mod>,
    var info: Info

)