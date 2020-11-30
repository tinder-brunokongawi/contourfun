package com.tinder.contourfun.rendering


sealed class CardRendering

sealed class VideoRendering: CardRendering()
data class VideoCardRendering(val url: String): VideoRendering()
data class HiddenFaceRendering(val url: String): VideoRendering()

data class ImageCardRendering(val url: String): CardRendering()