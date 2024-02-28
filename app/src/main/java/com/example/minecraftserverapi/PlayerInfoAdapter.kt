package com.example.minecraftserverapi

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.minecraftserverapi.models.Players
import com.squareup.picasso.Picasso
import java.net.URL


class PlayerInfoAdapter(private var playerList: Players): RecyclerView.Adapter<PlayerInfoAdapter.ViewHolder>() {
    companion object {
        val TAG = "ServerInfoAdapter"
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val uuidTextView: TextView
        val skinImageView: ImageView
        val layout: ConstraintLayout

        init {
            nameTextView = view.findViewById(R.id.textView_itemPlayer_name)
            uuidTextView = view.findViewById(R.id.textView_itemPlayer_uuid)
            skinImageView = view.findViewById(R.id.imageView_itemPlayer_skin)
            layout = view.findViewById(R.id.layout_itemPlayer)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_player, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.layout.context
        val player = playerList.list?.get(position)!!
        val name = player.name
        val uuid = player.uuid
        Picasso.get()
            .load("https://crafatar.com/avatars/$uuid")
            .resize(128, 128)
            .into(viewHolder.skinImageView)

        viewHolder.nameTextView.text = name
        viewHolder.uuidTextView.text = uuid
    }

    override fun getItemCount() = playerList.list?.size!!
}