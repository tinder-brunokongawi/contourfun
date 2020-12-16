package com.tinder.contourfun.card

import android.content.Context
import android.graphics.drawable.PaintDrawable
import android.widget.ImageView
import coil.load
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR
import com.tinder.contourfun.quadpanel.BaseCard
import com.tinder.contourfun.rendering.ImageCardRendering

// unused
class ImageCardCard @JvmOverloads constructor(
    context: Context,
    backgroundColor: Int = DEFAULT_BACKGROUND_COLOR,
    roundedCorner: Float = 0F
) : BaseCard<ImageCardRendering>(context) {

    private val imageView by lazy {
        ImageView(context).apply {
            layoutBy(
                x = matchParentX(),
                y = matchParentY()
            )
        }
    }

    init {
        contourHeightMatchParent()
        contourWidthMatchParent()
        background = PaintDrawable(backgroundColor).also {
            if (roundedCorner > 0F) it.setCornerRadius(roundedCorner)
        }
    }

    override fun render(rendering: ImageCardRendering) {
        imageView.load(rendering.url)
    }

}