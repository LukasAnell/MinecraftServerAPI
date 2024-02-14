package com.example.minecraftserverapi

import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.minecraftserverapi.models.ServerStatus
import java.util.Base64


class ServerListAdapter(private var serverStatusList: List<ServerStatus>): RecyclerView.Adapter<ServerListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ipAndPortTextView: TextView
        val versionTextView: TextView
        val iconImageView: ImageView
        val motdTextView: TextView
        val onlineCountTextView: TextView
        val nameTextView: TextView
        val layout: ConstraintLayout
        init {
            nameTextView = view.findViewById(R.id.textView_serverItem_name)
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.layout.context
        val server = serverStatusList[position]
        val name = server.hostname?.ifEmpty { "Minecraft Server" }
        val ipAndPort = "${server.ip}:${server.port}"
        val version = server.version
        val iconBase64 = server.icon.split(",").get(1)
        val motd = server.motd.toString()
        val onlineCount = "${server.players.online}/${server.players.max}"

        viewHolder.versionTextView.text = version
        viewHolder.motdTextView.text = motd
        viewHolder.onlineCountTextView.text = onlineCount
        viewHolder.ipAndPortTextView.text = ipAndPort

        val decodedString: ByteArray = Base64.getDecoder().decode(iconBase64)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        mImageView.setImageBitmap(decodedByte)

        viewHolder.iconImageView.drawable
    }

    override fun getItemCount() = serverStatusList.size
}