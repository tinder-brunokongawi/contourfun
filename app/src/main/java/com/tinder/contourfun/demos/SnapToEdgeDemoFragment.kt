package com.tinder.contourfun.demos

import android.content.Context
import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.doOnAttach
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import coil.load
import coil.transform.CircleCropTransformation
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR
import com.tinder.contourfun.R
import com.tinder.contourfun.contour.AxesSolver
import com.tinder.contourfun.quadpanel.BaseQuadPanel

class SnapToEdgeDemoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = SnapToEdgeDemo(requireContext())

    class SnapToEdgeDemo(context: Context) : ContourLayout(context) {

        companion object {
            private const val margin = 24
            private const val imageSize = 80
            private val buttonEmojiTexts = listOf(
                "\uD83D\uDC46\uD83C\uDFFE",
                "\uD83D\uDC49\uD83C\uDFFE",
                "\uD83D\uDC47\uD83C\uDFFE",
                "\uD83D\uDC48\uD83C\uDFFE"
            )
        }

        private val imageView by lazy {
            ImageView(context).apply {
                doOnAttach {
                    (it as ImageView).load(R.drawable.rocket) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }

        private val buttonAxesSolvers = listOf(
            AxesSolver(
                centerHorizontallyTo { parent.centerX() },
                topTo { parent.top() + margin.ydip }
            ),
            AxesSolver(
                rightTo { parent.right() - margin.xdip },
                centerVerticallyTo { parent.centerY() }
            ),
            AxesSolver(
                centerHorizontallyTo { parent.centerX() },
                bottomTo { parent.bottom() - margin.ydip }
            ),
            AxesSolver(
                leftTo { parent.left() + margin.xdip },
                centerVerticallyTo { parent.centerY() }
            )
        )

        val buttons = listOf(
            Button(context),
            Button(context),
            Button(context),
            Button(context)
        ).apply {
            forEachIndexed { index, button ->
                button.text = buttonEmojiTexts[index]
                button.layoutBy(buttonAxesSolvers[index].x, buttonAxesSolvers[index].y)
                button.setOnClickListener {
                    TransitionManager.beginDelayedTransition(
                        this@SnapToEdgeDemo,
                        ChangeBounds()
                            .setInterpolator(FastOutSlowInInterpolator())
                            .setDuration(BaseQuadPanel.TRANSITION_ANIMATION_DURATION)
                    )
                    when (index) {
                        0 -> {
                            imageView.rotation = 0F
                        }
                        1 -> {
                            imageView.rotation = 90F
                        }
                        2 -> {
                            imageView.rotation = 180F
                        }
                        3 -> {
                            imageView.rotation = 270F
                        }
                    }
                    imageView.updateLayoutBy(buttonAxesSolvers[index].x, buttonAxesSolvers[index].y)
                    it.isVisible = false
                }
            }
        }

        init {
            background = PaintDrawable(DEFAULT_BACKGROUND_COLOR)
            imageView.layoutBy(
                x = centerHorizontallyTo { parent.centerX() }.widthOf { imageSize.xdip },
                y = centerVerticallyTo { parent.centerY() }.heightOf { imageSize.ydip }
            )
        }
    }
}