package com.hiepsummer.practivcalexamplemvvm.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hiepsummer.practivcalexamplemvvm.R
import com.hiepsummer.practivcalexamplemvvm.model.Video

class VideoAdapter(val videolist: List<Video>?) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    private var mContext: Context? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.textViewName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.mContext = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.video_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return videolist!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mVideo = videolist!!.get(position)
        if (mVideo.name != null) {
            holder.tvName.setText(mVideo.name + "." + mVideo.type)
        }
        holder.tvName.setOnClickListener {
            if (mVideo.uri != null) {
                try {
                    //play in default brower device
//                    val intent = Intent()
//                    intent.setAction(Intent.ACTION_VIEW)
//                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
//                    intent.setData(Uri.parse(mVideo.uri))

                    // Play in webView at this app
                    val intent = Intent(mContext, PlayActivity::class.java)
                    intent.putExtra("uri", mVideo.uri)
                    mContext!!.startActivity(intent)

                } catch (e: Exception) {

                }
            }
        }
    }

}