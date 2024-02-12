package com.example.minecraftserverapi.api

import com.example.minecraftserverapi.models.ServerStatus
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerService {
    @GET("3/{address}")
    fun getJavaMcServerInfo(@Path("address") address: String): Call<ServerStatus>

    @GET("bedrock/3/{address}")
    fun getBedrockMcServerInfo(@Path("address") address: String): Call<ServerStatus>
}