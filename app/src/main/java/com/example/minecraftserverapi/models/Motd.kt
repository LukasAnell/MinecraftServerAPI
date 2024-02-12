package com.example.minecraftserverapi.models

data class Motd(
    var raw: List<String>,
    var clean: List<String>,
    var html: List<String>
)
