package com.example.minecraftserverapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.minecraftserverapi.databinding.ActivityServerInfoBinding
import com.example.minecraftserverapi.models.ServerStatus

class ServerInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServerInfoBinding
    private lateinit var server: ServerStatus
    companion object {
        const val TAG = "ServerInfoActivity"
        const val EXTRA_SERVER = "server"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        server = intent.getParcelableExtra(EXTRA_SERVER)!!
        Log.d(TAG, "server: $server")


    }
}