package com.example.minecraftserverapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minecraftserverapi.models.ServerStatus

class ServerAdapter(private var serverStatusList: List<ServerStatus>): RecyclerView.Adapter<ServerAdapter.ViewHolder>() {
    class ViewHolder {
        init {

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_server, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = serverStatusList.size
}