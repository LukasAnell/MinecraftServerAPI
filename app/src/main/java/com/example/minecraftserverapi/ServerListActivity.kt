package com.example.minecraftserverapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minecraftserverapi.databinding.ActivityServerListBinding

class ServerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServerListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server_list)
    }
}