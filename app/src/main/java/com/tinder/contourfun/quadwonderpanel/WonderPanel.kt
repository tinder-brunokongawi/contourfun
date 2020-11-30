package com.tinder.contourfun.quadwonderpanel

import android.content.Context
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import com.tinder.contourfun.COLORS
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR
import com.tinder.contourfun.card.VideoCard
import com.tinder.contourfun.quadpanel.BaseCard
import com.tinder.contourfun.quadpanel.BaseQuadPanel
import com.tinder.contourfun.quadpanel.PHI
import com.tinder.contourfun.rendering.CardRendering
import kotlin.math.pow

// experimental variation
class WonderPanel(context: Context) : BaseQuadPanel<CardRendering>(context) {

    companion object {
        private val PHI_POW_2 = PHI.pow(2)
    }

    init {
        setBackgroundColor(DEFAULT_BACKGROUND_COLOR)
        contourHeightMatchParent()
    }

    override fun createCard(index: Int): BaseCard<CardRendering> {
        val videoCard = VideoCard(context, COLORS[index])
        videoCard.setOnClickListener {
            focusOn(index)
        }
        return videoCard
    }

    override fun focusOn(index: Int) {
        TransitionManager.beginDelayedTransition(
            this,
            ChangeBounds()
                .setInterpolator(OvershootInterpolator(1F))
                .setDuration(TRANSITION_ANIMATION_DURATION)
                .addListener(
                    object : Transition.TransitionListener {

                        override fun onTransitionStart(transition: Transition) {
                            // no-op
                        }

                        override fun onTransitionEnd(transition: Transition) {
                            relayout()
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
        cards[index].updateLayoutBy(
            focusedAxesSolver.x,
            focusedAxesSolver.y
        )
        axesSolvers.swap(index, selected)
        cards.forEachIndexed { i, _ ->
            if (i != index) {
                cards[i].updateLayoutBy(
                    rightTo { parent.left() }.widthOf { 0.dip.toXInt() },
                    topTo {
                        (cards[index].bottom() + cards[index].height() * PHI_POW_2).toInt()
                    }.heightOf {
                        0.dip.toYInt()
                    }
                )
            }
            requestLayout()
        }
        selected = index
    }

    private fun relayout() {
        TransitionManager.beginDelayedTransition(
            this,
            ChangeBounds()
                .setInterpolator(OvershootInterpolator(1F))
                .setDuration(TRANSITION_ANIMATION_DURATION)
        )
        cards.forEachIndexed { i, card ->
            if (i != selected) {
                card.updateLayoutBy(
                    x = axesSolvers[i].x,
                    y = axesSolvers[i].y
                )
                requestLayout()
            }
        }
    }

}