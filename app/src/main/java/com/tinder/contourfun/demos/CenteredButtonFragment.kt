package com.tinder.contourfun.demos

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.COLORS

/* simple demo */
class CenteredButtonFragment : Fragment() {

    companion object {
        const val BUTTON_WIDTH = 174
        const val BUTTON_HEIGHT = 54
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CenteredButton(requireContext())

    @SuppressLint("SetTextI18n")
    class CenteredButton(context: Context): ContourLayout(context) {
        init {
            background = PaintDrawable(COLORS[1])
            Button(context).apply {
                text = "I AM A CENTERED BUTTON"
                layoutBy(
                    x = centerHorizontallyTo { parent.centerX() }.widthOf { BUTTON_WIDTH.xdip },
                    y = centerVerticallyTo { parent.centerY() }.heightOf { BUTTON_HEIGHT.ydip }
                )
            }
        }
    }
}