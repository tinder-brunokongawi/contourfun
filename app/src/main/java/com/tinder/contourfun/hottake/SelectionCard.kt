package com.tinder.contourfun.hottake

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.PaintDrawable
import android.view.Gravity
import android.widget.TextView
import androidx.core.view.updatePadding
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
        const val ELEVATION = 20F
        const val HORIZONTAL_PADDING = 16
        const val VERTICAL_PADDING = 16
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
        elevation = ELEVATION.dip
        updatePadding(left = HORIZONTAL_PADDING.dip, right = HORIZONTAL_PADDING.dip, top = 36.dip)

    }
}