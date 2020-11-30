package com.tinder.contourfun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.tinder.contourfun.quadwonderpanel.WonderPanel
import com.tinder.contourfun.videoconference.VideoPanel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(VideoPanel(this) /*WonderPanel(this)*/)
    }
}