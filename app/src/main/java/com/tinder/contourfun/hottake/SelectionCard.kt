package com.tinder.contourfun.hottake

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.PaintDrawable
import android.view.Gravity
import android.widget.TextView
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.PHI_2

class SelectionCard @JvmOverloads constructor(
    context: Context,
    backgroundColor: Int = BACKGROUND_COLOR.toInt(),
    private val cornerRadius: Float = CORNER_RADIUS
): ContourLayout(context) {

    companion object {
        const val BACKGROUND_COLOR = 0xFF38057C
        const val CORNER_RADIUS = 32F
    }

    private val promptTextView = TextView(context).apply {
        setTextColor(Color.WHITE)
        setLineSpacing(0f, PHI_2)
        setTypeface(typeface, Typeface.BOLD)
        gravity = Gravity.CENTER_HORIZONTAL
        textSize = 16F
    }

    init {
        background = PaintDrawable(backgroundColor).also { it.setCornerRadius(cornerRadius) }

    }
}