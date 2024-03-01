package com.example.minecraftserverapi

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minecraftserverapi.databinding.ActivityServerInfoBinding
import com.example.minecraftserverapi.models.ServerStatus
import java.util.Base64

class ServerInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServerInfoBinding
    private lateinit var server: ServerStatus
    companion object {
        const val TAG = "ServerInfoActivity"
        const val EXTRA_SERVER = "server"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        server = intent.getParcelableExtra(EXTRA_SERVER)!!
        Log.d(TAG, "server: $server")

        binding.textViewServerInfoIp.text = "IP: ${server.ip}"
        binding.textViewServerInfoMotd.text = Html.fromHtml(server.motd.html!!.joinToString("<br>"))
        binding.textViewServerInfoName.text = if (server.hostname == null) "Minecraft Server" else server.hostname
        binding.textViewServerInfoPort.text = "Port: ${server.port}"
        binding.textViewServerInfoVersion.text = "Version: ${server.version}"
        binding.textViewServerInfoPlayerCount.text = "Online: ${server.players.online}/${server.players.max}"
        val iconBase64 = server.icon.split(",")[1]
        val decodedString: ByteArray = Base64.getDecoder().decode(iconBase64)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        binding.imageViewServerInfoLogo.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, 256, 256, false))

        if(server.players.list != null) {
            refreshList()
        }

        binding.buttonServerInfoBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }

    private fun refreshList() {
        val playerInfoAdapter = PlayerInfoAdapter(server.players)

        val recyclerView: RecyclerView = binding.recyclerViewServerInfoPlayerList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = playerInfoAdapter
    }
}