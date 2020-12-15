package com.tinder.contourfun.hottake

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.core.view.updatePadding
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.PHI
import com.tinder.contourfun.R
import kotlin.math.pow

// for demo only
class HotTakeView(context: Context): ContourLayout(context) {

    companion object {
        val GRADIENT_COLORS = intArrayOf(0xFFF05CFC.toInt(), 0xFF6A82FB.toInt())
    }

    private val selectionCard = SelectionCard(context)
    var onClose: (() -> Unit)? = null

    init {
        contourHeightMatchParent()
        contourWidthMatchParent()
        background = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, GRADIENT_COLORS)
        ImageView(context).apply {
            setImageResource(R.drawable.ic_close)
            updatePadding(top = 36.dip, right = 36.dip)
            layoutBy(
                x = rightTo { parent.right() },
                y = topTo { parent.top() }
            )
            setOnClickListener {
                onClose?.invoke()
            }
        }
        selectionCard.layoutBy(
            x = centerHorizontallyTo { parent.centerX() }.widthOf {
                (width * PHI + (width * PHI.pow(3)) / 2).toInt().toXInt()
            },
            y = centerVerticallyTo {
                parent.centerY() - (height * PHI.pow(7)).toInt().toYInt()
            }.heightOf {
                (height * PHI + height * PHI.pow(5)).toInt().toYInt()
            }
        )
    }

}