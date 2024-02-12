package com.example.minecraftserverapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.minecraftserverapi.models.ServerStatus

class ServerListAdapter(private var serverStatusList: List<ServerStatus>): RecyclerView.Adapter<ServerListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ipAndPortTextView: TextView
        val versionTextView: TextView
        val iconImageView: ImageView
        val motdTextView: TextView
        val onlineCountTextView: TextView
        val layout: ConstraintLayout
        init {
            ipAndPortTextView = view.findViewById(R.id.textView_serverItem_ipAndPort)
            versionTextView = view.findViewById(R.id.textView_serverItem_version)
            iconImageView = view.findViewById(R.id.imageView_serverItem_icon)
            motdTextView = view.findViewById(R.id.textView_serverItem_motd)
            onlineCountTextView = view.findViewById(R.id.textView_serverItem_onlineCount)
            layout = view.findViewById(R.id.layout_itemServer)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_server, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.layout.context
        val server = serverStatusList[position]
        val ipAndPort = "${server.ip}:${server.port}"
        val version = server.version
        val iconBase64 = server.icon.split(",")[1]
        val motd = server.motd
        val onlineCount = "${server.players.online}/${server.players.max}"


    }

    override fun getItemCount() = serverStatusList.size
}