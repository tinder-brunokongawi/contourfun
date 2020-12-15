package com.tinder.contourfun.rocketlaunch

import android.content.Context
import android.graphics.drawable.PaintDrawable
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.doOnAttach
import androidx.core.view.isVisible
import coil.load
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR
import com.tinder.contourfun.R
import com.tinder.contourfun.contour.AxesSolver
import com.tinder.contourfun.quadpanel.BaseQuadPanel

class RocketLaunchView(context: Context) : ContourLayout(context) {

    companion object {
        private const val margin = 24
        private const val rocketSize = 60
        private const val flameSize = 50
        private val buttonEmojiTexts = listOf(
            "\uD83D\uDC46\uD83C\uDFFE",
            "\uD83D\uDC49\uD83C\uDFFE",
            "\uD83D\uDC47\uD83C\uDFFE",
            "\uD83D\uDC48\uD83C\uDFFE"
        )
    }

    private val rocketView = ImageView(context).apply {
        doOnAttach {
            (it as ImageView).load(R.drawable.rocket) {
                crossfade(true)
            }
        }
    }

    private val flameImageView = ImageView(context).apply {
        doOnAttach {
            load(R.drawable.flame) {
                crossfade(true)
            }
        }
        layoutBy(
            x = centerHorizontallyTo { rocketView.centerX() }.widthOf { flameSize.xdip },
            y = centerVerticallyTo { rocketView.centerY() }.heightOf { flameSize.ydip }
        )
    }

    private val buttonAxesSolvers = listOf(
        AxesSolver(
            centerHorizontallyTo { parent.centerX() }.widthOf { rocketSize.xdip },
            topTo { parent.top() + margin.ydip }.heightOf { rocketSize.ydip }
        ),
        AxesSolver(
            rightTo { parent.right() - margin.xdip }.widthOf { rocketSize.xdip },
            centerVerticallyTo { parent.centerY() }.heightOf { rocketSize.ydip }
        ),
        AxesSolver(
            centerHorizontallyTo { parent.centerX() }.widthOf { rocketSize.xdip },
            bottomTo { parent.bottom() - margin.ydip }.heightOf { rocketSize.ydip }
        ),
        AxesSolver(
            leftTo { parent.left() + margin.xdip }.widthOf { rocketSize.xdip },
            centerVerticallyTo { parent.centerY() }.heightOf { rocketSize.ydip }
        )
    )

    init {
        background = PaintDrawable(DEFAULT_BACKGROUND_COLOR)
        contourHeightMatchParent()
        contourWidthMatchParent()
        for (x in 0 until 4) {
            val button = Button(context)
            button.text = buttonEmojiTexts[x]
            button.layoutBy(buttonAxesSolvers[x].x, buttonAxesSolvers[x].y)
            button.setOnClickListener {
                flameImageView.isVisible = true
                TransitionManager.beginDelayedTransition(
                    this@RocketLaunchView,
                    ChangeBounds()
                        .setInterpolator(OvershootInterpolator(1F))
                        .setDuration(BaseQuadPanel.TRANSITION_ANIMATION_DURATION)
                        .addListener(
                            object : Transition.TransitionListener {

                                override fun onTransitionStart(transition: Transition) {
                                }

                                override fun onTransitionEnd(transition: Transition) {
                                    flameImageView.visibility = View.INVISIBLE
                                    flameImageView.updateLayoutBy(
                                        x = centerHorizontallyTo { rocketView.centerX() }.widthOf { flameSize.xdip },
                                        y = centerVerticallyTo { rocketView.centerY() }.heightOf { flameSize.ydip }
                                    )
                                }

                                override fun onTransitionCancel(transition: Transition) {
                                    // no-op
                                }

                                override fun onTransitionPause(transition: Transition) {
                                    // no-op
                                }

                                override fun onTransitionResume(transition: Transition) {
                                    // no-op
                                }
                            }
                        )
                )
                when (x) {
                    0 -> {
                        rocketView.rotation = 0F
                        flameImageView.rotation = 0F
                        flameImageView.updateLayoutBy(
                            x = centerHorizontallyTo { rocketView.centerX() }.widthOf { flameSize.xdip },
                            y = topTo { rocketView.bottom() }.heightOf { flameSize.ydip }
                        )
                    }
                    1 -> {
                        rocketView.rotation = 90F
                        flameImageView.rotation = 90F
                        flameImageView.updateLayoutBy(
                            x = rightTo { rocketView.left() }.widthOf { flameSize.xdip },
                            y = centerVerticallyTo { rocketView.centerY() }.heightOf { flameSize.ydip }
                        )
                    }
                    2 -> {
                        rocketView.rotation = 180F
                        flameImageView.rotation = 180F
                        flameImageView.updateLayoutBy(
                            x = centerHorizontallyTo { rocketView.centerX() }.widthOf { flameSize.xdip },
                            y = bottomTo { rocketView.top() }.heightOf { flameSize.ydip }
                        )
                    }
                    3 -> {
                        rocketView.rotation = 270F
                        flameImageView.rotation = 270F
                        flameImageView.updateLayoutBy(
                            x = leftTo { rocketView.right() }.widthOf { flameSize.xdip },
                            y = centerVerticallyTo { rocketView.centerY() }.heightOf { flameSize.ydip }
                        )
                    }
                }
                rocketView.updateLayoutBy(
                    buttonAxesSolvers[x].x,
                    buttonAxesSolvers[x].y
                )
                it.isVisible = false
            }
        }
        rocketView.layoutBy(
            x = centerHorizontallyTo { parent.centerX() }.widthOf { rocketSize.xdip },
            y = centerVerticallyTo { parent.centerY() }.heightOf { rocketSize.ydip }
        )
    }
}