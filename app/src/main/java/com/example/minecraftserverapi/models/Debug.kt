package com.example.minecraftserverapi.models

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
)
