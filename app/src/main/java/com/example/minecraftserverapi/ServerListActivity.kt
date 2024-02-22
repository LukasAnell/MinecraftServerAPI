package com.example.minecraftserverapi

import android.content.Intent
import android.os.Build
import java.util.ArrayList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minecraftserverapi.databinding.ActivityServerListBinding
import com.example.minecraftserverapi.models.ServerStatus

class ServerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServerListBinding
    private lateinit var serverStatusList: ArrayList<ServerStatus>

    companion object {
        const val TAG = "ServerListActivity"
        const val EXTRA_SERVER = "serverList"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        serverStatusList = intent.getParcelableArrayListExtra(EXTRA_SERVER)!!
        Log.d(TAG, "serverStatusList: $serverStatusList")
        refreshList()
        createListeners()
    }

    private fun createListeners() {
        binding.buttonServerListBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }

    private fun refreshList() {
        val serverListAdapter = ServerListAdapter(serverStatusList)

        val recyclerView: RecyclerView = binding.recyclerViewServerList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = serverListAdapter
    }
}