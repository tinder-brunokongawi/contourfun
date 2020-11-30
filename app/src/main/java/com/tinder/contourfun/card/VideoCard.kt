package com.tinder.contourfun.card

import android.content.Context
import android.graphics.drawable.PaintDrawable
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR
import com.tinder.contourfun.quadpanel.BaseCard
import com.tinder.contourfun.rendering.CardRendering
import com.tinder.contourfun.rendering.HiddenFaceRendering
import com.tinder.contourfun.rendering.VideoCardRendering

class VideoCard @JvmOverloads constructor(
    context: Context,
    backgroundColor: Int = DEFAULT_BACKGROUND_COLOR,
    roundedCorner: Float = 0.0F
) : BaseCard<CardRendering>(context) {

    init {
        background = PaintDrawable(backgroundColor).also {
            if (roundedCorner > 0.0F) it.setCornerRadius(roundedCorner)
        }
    }

    override fun render(rendering: CardRendering) {
        when (rendering) {
            is HiddenFaceRendering -> {

            }
            is VideoCardRendering -> {

            }
            else -> {

            }
        }
    }
}