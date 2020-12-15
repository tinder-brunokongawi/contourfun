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
        private const val MARGIN = 24
        private const val ROCKET_SIZE = 60
        private const val ROCKET_ELEVATION = 10F
        private const val FLAME_SIZE = 50
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
            it.elevation = ROCKET_ELEVATION
        }
    }

    private val flameImageView = ImageView(context).apply {
        doOnAttach {
            load(R.drawable.flame) {
                crossfade(true)
            }
        }
        layoutBy(
            x = centerHorizontallyTo { rocketView.centerX() }.widthOf { FLAME_SIZE.xdip },
            y = centerVerticallyTo { rocketView.centerY() }.heightOf { FLAME_SIZE.ydip }
        )
    }

    private val buttonAxesSolvers = listOf(
        AxesSolver(
            centerHorizontallyTo { parent.centerX() }.widthOf { ROCKET_SIZE.xdip },
            topTo { parent.top() + MARGIN.ydip }.heightOf { ROCKET_SIZE.ydip }
        ),
        AxesSolver(
            rightTo { parent.right() - MARGIN.xdip }.widthOf { ROCKET_SIZE.xdip },
            centerVerticallyTo { parent.centerY() }.heightOf { ROCKET_SIZE.ydip }
        ),
        AxesSolver(
            centerHorizontallyTo { parent.centerX() }.widthOf { ROCKET_SIZE.xdip },
            bottomTo { parent.bottom() - MARGIN.ydip }.heightOf { ROCKET_SIZE.ydip }
        ),
        AxesSolver(
            leftTo { parent.left() + MARGIN.xdip }.widthOf { ROCKET_SIZE.xdip },
            centerVerticallyTo { parent.centerY() }.heightOf { ROCKET_SIZE.ydip }
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
                                        x = centerHorizontallyTo { rocketView.centerX() }.widthOf { FLAME_SIZE.xdip },
                                        y = centerVerticallyTo { rocketView.centerY() }.heightOf { FLAME_SIZE.ydip }
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
                            x = centerHorizontallyTo { rocketView.centerX() }.widthOf { FLAME_SIZE.xdip },
                            y = topTo { rocketView.bottom() }.heightOf { FLAME_SIZE.ydip }
                        )
                    }
                    1 -> {
                        rocketView.rotation = 90F
                        flameImageView.rotation = 90F
                        flameImageView.updateLayoutBy(
                            x = rightTo { rocketView.left() }.widthOf { FLAME_SIZE.xdip },
                            y = centerVerticallyTo { rocketView.centerY() }.heightOf { FLAME_SIZE.ydip }
                        )
                    }
                    2 -> {
                        rocketView.rotation = 180F
                        flameImageView.rotation = 180F
                        flameImageView.updateLayoutBy(
                            x = centerHorizontallyTo { rocketView.centerX() }.widthOf { FLAME_SIZE.xdip },
                            y = bottomTo { rocketView.top() }.heightOf { FLAME_SIZE.ydip }
                        )
                    }
                    3 -> {
                        rocketView.rotation = 270F
                        flameImageView.rotation = 270F
                        flameImageView.updateLayoutBy(
                            x = leftTo { rocketView.right() }.widthOf { FLAME_SIZE.xdip },
                            y = centerVerticallyTo { rocketView.centerY() }.heightOf { FLAME_SIZE.ydip }
                        )
                    }
                }
                rocketView.updateLayoutBy(
                    buttonAxesSolvers[x].x,
                    buttonAxesSolvers[x].y
                )
            }
        }
        rocketView.layoutBy(
            x = centerHorizontallyTo { parent.centerX() }.widthOf { ROCKET_SIZE.xdip },
            y = centerVerticallyTo { parent.centerY() }.heightOf { ROCKET_SIZE.ydip }
        )
    }
}