package com.example.minecraftserverapi

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.text.Html
import android.util.Log
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
    companion object {
        val TAG = "ServerListAdapter"
    }

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
        val name = if (server.hostname == null) "Minecraft Server" else server.hostname
        val ipAndPort = "${server.ip}:${server.port}"
        val version = server.protocol.name
        val iconBase64 = server.icon.split(",")[1]
        val motd = Html.fromHtml(server.motd.html!!.joinToString("<br>"))
        val onlineCount = "${server.players.online}/${server.players.max}"

        viewHolder.versionTextView.text = version
        viewHolder.motdTextView.text = motd
        viewHolder.onlineCountTextView.text = onlineCount
        viewHolder.ipAndPortTextView.text = ipAndPort
        viewHolder.nameTextView.text = name

        val decodedString: ByteArray = Base64.getDecoder().decode(iconBase64)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        viewHolder.iconImageView.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, 256, 256, false))

        viewHolder.layout.setOnClickListener {
            val intent = Intent(context, ServerInfoActivity::class.java)
            intent.putExtra(ServerInfoActivity.EXTRA_SERVER, server)

            context.startActivity(intent)
        }
    }

    override fun getItemCount() = serverStatusList.size
}