package com.hiepsummer.practivcalexamplemvvm.view


import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.hiepsummer.practivcalexamplemvvm.R
import kotlinx.android.synthetic.main.activity_play.*


class PlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "演奏する"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        val uri = intent.getStringExtra("uri")
        webview.loadUrl(uri)
        webview.setWebViewClient(WebViewClient())

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
