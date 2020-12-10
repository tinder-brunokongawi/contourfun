package com.tinder.contourfun.demos

import android.content.Context
import android.widget.Button
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.util.distinctObservable

/* demo only */
class DemoListLayout(context: Context) : ContourLayout(context) {

    companion object {
        const val BUTTON_WIDTH = 240
        const val BUTTON_HEIGHT = 48
        const val TOP_MARGIN = 100
        const val BUTTON_MARGIN = 24
    }

    var buttonActions by distinctObservable(mutableListOf<Pair<String, () -> Unit>>()) {
        removeAllViews()
        it.forEachIndexed { index, buttonAction ->
            val button = Button(context)
            button.text = buttonAction.first
            button.setOnClickListener {
                buttonAction.second.invoke()
            }
            button.layoutBy(
                x = centerHorizontallyTo {
                    parent.centerX()
                }.widthOf {
                    BUTTON_WIDTH.xdip
                },
                y = topTo {
                    parent.top() + (TOP_MARGIN + index * (BUTTON_MARGIN + BUTTON_HEIGHT)).ydip
                }.heightOf {
                    BUTTON_HEIGHT.ydip
                }
            )
        }
    }
}