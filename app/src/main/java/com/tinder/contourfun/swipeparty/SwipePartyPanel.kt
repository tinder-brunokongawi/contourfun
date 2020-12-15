package com.tinder.contourfun.swipeparty

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.PaintDrawable
import android.widget.ImageView
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR

class SwipePartyPanel(context: Context): ContourLayout(context) {

    private val ownerImageView = ImageView(context).apply {

    }

    init {
        contourHeightMatchParent()
        contourWidthMatchParent()
        background = PaintDrawable(Color.BLACK)
    }
}