package com.tinder.contourfun.videoconference

import android.content.Context
import com.tinder.contourfun.COLORS
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR
import com.tinder.contourfun.quadpanel.BaseCard
import com.tinder.contourfun.quadpanel.BaseQuadPanel
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import com.tinder.contourfun.card.VideoCard
import com.tinder.contourfun.rendering.CardRendering

class VideoPanel(context: Context) : BaseQuadPanel<CardRendering>(context) {

    init {
        setBackgroundColor(DEFAULT_BACKGROUND_COLOR)
        contourHeightMatchParent()
        contourWidthMatchParent()
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
                .setInterpolator(OvershootInterpolator(OVERSHOOT_TENSION))
                .setDuration(TRANSITION_ANIMATION_DURATION)
        )
        axesSolvers.swap(selected, index)
        cards[selected].updateLayoutBy(
            x = axesSolvers[selected].x,
            y = axesSolvers[selected].y
        )
        cards[index].updateLayoutBy(
            x = axesSolvers[index].x,
            y = axesSolvers[index].y
        )
        selected = index
    }
}