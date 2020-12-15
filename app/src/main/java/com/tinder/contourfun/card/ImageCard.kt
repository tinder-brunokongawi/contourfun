package com.tinder.contourfun.card

import android.content.Context
import android.graphics.drawable.PaintDrawable
import android.widget.ImageView
import coil.load
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR
import com.tinder.contourfun.quadpanel.BaseCard
import com.tinder.contourfun.rendering.ImageCardRendering

class ImageCardCard @JvmOverloads constructor(
    context: Context,
    backgroundColor: Int = DEFAULT_BACKGROUND_COLOR,
    roundedCorner: Float = 0.0F
) : BaseCard<ImageCardRendering>(context) {

    private val imageView by lazy {
        ImageView(context).apply {
            layoutBy(
                x = leftTo { parent.left() }.rightTo { parent.right() },
                y = topTo { parent.top() }.bottomTo { parent.bottom() }
            )
        }
    }

    init {
        contourHeightMatchParent()
        contourWidthMatchParent()
        background = PaintDrawable(backgroundColor).also {
            if (roundedCorner > 0.0F) it.setCornerRadius(roundedCorner)
        }
    }

    override fun render(rendering: ImageCardRendering) {
        imageView.load(rendering.url)
    }

}