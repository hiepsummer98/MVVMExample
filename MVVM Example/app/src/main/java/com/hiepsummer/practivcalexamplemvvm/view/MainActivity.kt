package com.hiepsummer.practivcalexamplemvvm.view

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiepsummer.practivcalexamplemvvm.R
import com.hiepsummer.practivcalexamplemvvm.model.Video
import com.hiepsummer.practivcalexamplemvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var mainViewModel: MainViewModel? = null
    var mVideoAdapter: VideoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "動画"

        if (isConnectedToNetWork()) {
            setContentView(R.layout.activity_main)
            mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            getVideo()
            swiperefresh.setOnRefreshListener { getVideo() }

        } else {
            builder(this)?.show()
        }

    }

    fun getVideo() {
        swiperefresh.setRefreshing(true)

        mainViewModel?.getAllVideo()?.observe(this, Observer { listvideo ->
            swiperefresh.setRefreshing(false)
            preparrecyclerView(listvideo)
        })

    }

    private fun preparrecyclerView(videoList: List<Video>?) {
        mVideoAdapter = VideoAdapter(videoList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            listVideoRecyclerView.setLayoutManager(LinearLayoutManager(this))

        } else {
            listVideoRecyclerView.setLayoutManager(GridLayoutManager(this, 4))
        }
        listVideoRecyclerView.setItemAnimator(DefaultItemAnimator())
        listVideoRecyclerView.setAdapter(mVideoAdapter)
        mVideoAdapter!!.notifyDataSetChanged()
    }

    fun isConnectedToNetWork(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }

    fun builder(c: Context?): AlertDialog.Builder? {
        val builder =
            AlertDialog.Builder(c!!)
        builder.setTitle("インターネット接続なし")
        builder.setMessage("アプリに接続できません。 インターネット接続を確認して、もう一度お試しください!")
        builder.setPositiveButton(
            "Ok"
        ) { dialog, which -> finish() }
        return builder
    }

}
