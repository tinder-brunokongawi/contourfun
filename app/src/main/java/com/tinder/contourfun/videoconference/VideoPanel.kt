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
import com.tinder.contourfun.rendering.HiddenFaceRendering
import com.tinder.contourfun.rendering.VideoCardRendering

class VideoPanel(context: Context) : BaseQuadPanel<CardRendering>(context) {

    // random videos/images
    private val renderings = listOf(
        VideoCardRendering("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8"),
        HiddenFaceRendering("https://ca.slack-edge.com/E9ZDCUQER-WD2QFGJ3E-9b07d6cbe54c-512"),
        HiddenFaceRendering("https://ca.slack-edge.com/E9ZDCUQER-WD2PWAX9B-5ab81568dac9-512"),
        HiddenFaceRendering("https://ca.slack-edge.com/E9ZDCUQER-WD2QFGV3N-ce754b2bcd79-512"),
        HiddenFaceRendering("https://ca.slack-edge.com/E9ZDCUQER-W015Z15HGT1-6cca8774c231-512")
    )

    init {
        contourHeightMatchParent()
        contourWidthMatchParent()
        setBackgroundColor(DEFAULT_BACKGROUND_COLOR)
        render(renderings)
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