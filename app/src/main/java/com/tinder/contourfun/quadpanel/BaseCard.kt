package com.tinder.contourfun.quadpanel

import android.content.Context
import com.squareup.contour.ContourLayout

abstract class BaseCard<T>(context: Context) : ContourLayout(context) {

    abstract fun render(rendering: T)
}