package com.tinder.contourfun.card

import android.content.Context
import android.graphics.drawable.PaintDrawable
import android.widget.ImageView
import coil.load
import coil.size.Scale
import com.tinder.contourfun.DEFAULT_BACKGROUND_COLOR
import com.tinder.contourfun.quadpanel.BaseCard
import com.tinder.contourfun.rendering.CardRendering
import com.tinder.contourfun.rendering.HiddenFaceRendering
import com.tinder.contourfun.rendering.VideoCardRendering
import com.tinder.video.TinderPlayerView
import com.tinder.video.TinderVideoPlayer

/* demo only */
class VideoCard @JvmOverloads constructor(
    context: Context,
    backgroundColor: Int = DEFAULT_BACKGROUND_COLOR,
    roundedCorner: Float = 0F
) : BaseCard<CardRendering>(context) {

    private val playerView by lazy {
        TinderPlayerView(context).apply {
            layoutBy(
                x = matchParentX(),
                y = matchParentY()
            )
        }
    }

    private val imageView by lazy {
        ImageView(context).apply {
            layoutBy(
                x = matchParentX(),
                y = matchParentY()
            )
        }
    }

    private val videoPlayerBuilder by lazy {
        TinderVideoPlayer.Builder()
            .context(context)
            .mediaType(TinderVideoPlayer.MediaType.HLS)
    }

    init {
        contourHeightMatchParent()
        contourWidthMatchParent()
        background = PaintDrawable(backgroundColor).also {
            if (roundedCorner > 0F) it.setCornerRadius(roundedCorner)
        }
    }

    override fun render(rendering: CardRendering) {
        when (rendering) {
            is HiddenFaceRendering -> {
                imageView.load(rendering.url) {
                    crossfade(true)
                    scale(Scale.FILL)
                }
            }
            is VideoCardRendering -> {
                val videoPlayer = videoPlayerBuilder.url(rendering.url)
                    .id(rendering.url)
                    .build()
                videoPlayer.attachToView(playerView)
                videoPlayer.prepare()
                videoPlayer.play()
            }
            else -> {
                /* no op */
            }
        }
    }
}