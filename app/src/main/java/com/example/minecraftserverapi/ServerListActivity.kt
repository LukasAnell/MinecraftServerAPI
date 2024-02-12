package com.example.minecraftserverapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minecraftserverapi.databinding.ActivityServerListBinding
import com.example.minecraftserverapi.models.ServerStatus

class ServerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServerListBinding
    private lateinit var serverStatusList: List<ServerStatus>

    companion object {
        const val TAG = "ServerListActivity"
        const val EXTRA_SERVER_LIST = "serverList"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        serverStatusList = intent.getParcelableArrayListExtra(EXTRA_SERVER_LIST)!!
        Log.d(TAG, "serverStatusList: $serverStatusList")

        refreshList()
    }

    private fun refreshList() {
        val serverListAdapter = ServerListAdapter(serverStatusList)

        val recyclerView: RecyclerView = binding.recyclerViewServerList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = serverListAdapter
    }
}