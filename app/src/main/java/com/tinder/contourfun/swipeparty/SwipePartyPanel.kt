package com.tinder.contourfun.swipeparty

import android.content.Context
import android.graphics.drawable.PaintDrawable
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR

class SwipePartyPanel(context: Context): ContourLayout(context) {

    init {
        contourHeightMatchParent()
        contourWidthMatchParent()
        background = PaintDrawable(DEFAULT_BACKGROUND_COLOR)
    }
}