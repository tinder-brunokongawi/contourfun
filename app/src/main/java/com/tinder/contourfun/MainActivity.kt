package com.tinder.contourfun

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tinder.contourfun.demos.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        val demoListLayout = DemoListLayout(this)
        setContentView(demoListLayout)
        demoListLayout.buttonActions = mutableListOf(
            Pair(CenteredButtonFragment::class.simpleName.toString(), {
                supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, CenteredButtonFragment())
                    .addToBackStack(null)
                    .commit()
            }),
            Pair(SnapToEdgeDemoFragment::class.simpleName.toString(), {
                supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, SnapToEdgeDemoFragment())
                    .addToBackStack(null)
                    .commit()
            }),
            Pair(VideoPanelFragment::class.simpleName.toString(), {
                supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, VideoPanelFragment())
                    .addToBackStack(null)
                    .commit()
            }),
            Pair(WonderPanelFragment::class.simpleName.toString(), {
                supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, WonderPanelFragment())
                    .addToBackStack(null)
                    .commit()

            }),
            Pair(SwipePartyFragment::class.simpleName.toString(), {
                supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, SwipePartyFragment())
                    .addToBackStack(null)
                    .commit()
            })
        )

    }


    private fun startFragment() {

    }
}