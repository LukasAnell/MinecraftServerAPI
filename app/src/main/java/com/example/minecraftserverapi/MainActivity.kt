package com.example.minecraftserverapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.minecraftserverapi.api.ServerService
import com.example.minecraftserverapi.api.RetrofitHelper
import com.example.minecraftserverapi.databinding.ActivityMainBinding
import com.example.minecraftserverapi.models.Info
import com.example.minecraftserverapi.models.ServerStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var serverStatus: ServerStatus
    private var serverStatusList = arrayListOf<ServerStatus>()

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun getStatus(address: String, isBedrock: Boolean) {
        val retrofit = RetrofitHelper.getInstance()
        val earthquakeService = retrofit.create(ServerService::class.java)
        val serverCall = if (isBedrock) earthquakeService.getBedrockMcServerInfo(address) else earthquakeService.getJavaMcServerInfo(address)
        serverCall.enqueue(object: Callback<ServerStatus> {
            override fun onResponse(
                call: Call<ServerStatus>,
                response: Response<ServerStatus>
            ) {
                // this is where you get your data
                // this is where you will set up your adapter for RecyclerView
                // don't forget a null check before trying to use the data
                // response.body() contains the object in the <> after response
                serverStatus = response.body()!!
                checkNull()
                // 162.55.135.136
                if(serverStatus.online) {
                    serverStatusList.add(serverStatus)
                    Log.d(TAG, "serverStatusList: $serverStatusList")
                }
                Log.d(TAG, "hostname: ${serverStatus.hostname}")

                sendData()
            }

            override fun onFailure(call: Call<ServerStatus>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}\n${t.stackTrace}")
            }
        })
    }

    private fun checkNull() {
        if(serverStatus.players.list == null) {
            serverStatus.players.list = listOf()
        }
        if(serverStatus.plugins == null) {
            serverStatus.plugins = listOf()
        }
        if(serverStatus.mods == null) {
            serverStatus.mods = listOf()
        }
        if(serverStatus.info == null) {
            serverStatus.info = Info(listOf(), listOf(), listOf())
        }
    }

    private fun sendData() {
        val intent = Intent(this, ServerListActivity::class.java)

        intent.putExtra(ServerListActivity.EXTRA_SERVER, serverStatusList)
        startActivity(intent)
    }

    private fun setListeners() {
        binding.buttonMainSubmit.setOnClickListener {
            val inputAddress = binding.editTextMainInputAddress.text.toString()
            var inputPort = binding.editTextMainInputPort.text.toString()
            Log.d(TAG, inputAddress)
            if(inputAddress.isEmpty()) {
                Toast.makeText(this, "You must input a server address!", Toast.LENGTH_SHORT).show()
            } else {
                // check if is valid IP address
                val isIpValid = Patterns.IP_ADDRESS.matcher(inputAddress).matches()
                val isPortValid = if(inputPort.isEmpty()) true else inputPort.toInt() in 1..65535

                Log.d(TAG, "IP isValid: $isIpValid")
                Log.d(TAG, "port isValid: $isPortValid")

                if(!isIpValid) {
                    Toast.makeText(this, "You must input a valid server address!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(!isPortValid) {
                    Toast.makeText(this, "You must input a valid port!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if(inputPort.isNotEmpty()) {
                    inputPort = ":$inputPort"
                    getStatus("$inputAddress$inputPort", binding.switchMainIsBedrock.isActivated)
                } else {
                    // loopThroughPorts(inputAddress, binding.switchMainIsBedrock.isActivated)
                }
            }
        }
    }

    /*
    private fun loopThroughPorts(ip: String, isBedrock: Boolean) {
        for (i in 1..65535) {
            getStatus("$ip:$i", isBedrock)
            if (serverStatus.online) {
                serverStatusList.add(serverStatus)
            }
        }
    }
     */
}