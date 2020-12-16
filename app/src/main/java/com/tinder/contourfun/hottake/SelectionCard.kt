package com.tinder.contourfun.hottake

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.PaintDrawable
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.Gravity
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.PHI_2
import com.tinder.contourfun.demos.DemoListLayout

class SelectionCard @JvmOverloads constructor(
    context: Context,
    backgroundColor: Int = BACKGROUND_COLOR.toInt(),
    private val cornerRadius: Float = CORNER_RADIUS
) : ContourLayout(context) {

    companion object {
        private const val BACKGROUND_COLOR = 0xFF38057C
        private const val CORNER_RADIUS = 32F
        private const val ELEVATION = 20F
        private const val HORIZONTAL_PADDING = 16
        private const val HORIZONTAL_MARGIN = 16
        private const val VERTICAL_PADDING = 24
        private const val PROMPT_TEXT_SIZE = 16F
        private const val CHOICE_TOP_MARGIN = 24
        private const val CHOICE_HEIGHT = 64
        private const val TRANSITION_ANIMATION_DURATION = 400L
        private const val OVERSHOOT_TENSION = .5F
        private const val CHOICE_CORNER_RADIUS = 15F
        private const val CHOICE_BUTTON_WIDTH = 260
        private val CHOICE_COLORS = intArrayOf(0xFF386ede.toInt(), 0xFF793bb5.toInt())
        private const val DUMMY_PROMPT_TEXT = "Why did you choose to save the puppy or Alexis?"
        private val CHOICE_TEXTS = listOf(
            "Graham and Alexis suck",
            "Human lives are precious",
            "Puppies > People",
            "No reason"
        )
    }

    private val promptTextView = TextView(context).apply {
        setTextColor(Color.WHITE)
        setLineSpacing(0F, PHI_2)
        setTypeface(typeface, Typeface.BOLD)
        gravity = Gravity.CENTER_HORIZONTAL
        textSize = PROMPT_TEXT_SIZE
        text = DUMMY_PROMPT_TEXT
        layoutBy(
            matchParentX(marginLeft = HORIZONTAL_MARGIN.dip, marginRight = HORIZONTAL_MARGIN.dip),
            topTo { parent.top() }
        )
    }

    init {
        background = PaintDrawable(backgroundColor).also { it.setCornerRadius(cornerRadius) }
        elevation = ELEVATION.dip
        updatePadding(
            left = HORIZONTAL_PADDING.dip,
            right = HORIZONTAL_PADDING.dip,
            top = VERTICAL_PADDING.dip,
            bottom = VERTICAL_PADDING.dip
        )
        CHOICE_TEXTS.forEachIndexed { index, text ->
            val button = Button(context)
            button.text = text
            button.background =
                GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, CHOICE_COLORS).also {
                    it.cornerRadius = CHOICE_CORNER_RADIUS
                }
            button.setOnClickListener {
                TransitionManager.beginDelayedTransition(
                    this,
                    ChangeBounds()
                        .setInterpolator(OvershootInterpolator(OVERSHOOT_TENSION))
                        .setDuration(TRANSITION_ANIMATION_DURATION)
                )
                children.filter { it != button }.forEach {
                    it.isVisible = false
                }
                button.updateLayoutBy(
                    x = matchParentX(),
                    y = matchParentY()
                )
            }
            button.layoutBy(
                x = centerHorizontallyTo {
                    parent.centerX()
                }.widthOf {
                    CHOICE_BUTTON_WIDTH.xdip
                },
                y = topTo {
                    promptTextView.bottom() + (CHOICE_TOP_MARGIN + index * (CHOICE_TOP_MARGIN / 2 + CHOICE_HEIGHT)).ydip
                }.heightOf {
                    CHOICE_HEIGHT.ydip
                }
            )
        }
    }
}