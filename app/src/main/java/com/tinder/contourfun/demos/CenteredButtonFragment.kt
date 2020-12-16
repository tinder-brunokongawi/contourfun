package com.tinder.contourfun.demos

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
        private const val BUTTON_WIDTH = 174
        private const val BUTTON_HEIGHT = 54
        private const val DUMMY_BUTTON_TEXT = "I AM A CENTERED BUTTON"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CenteredButton(requireContext())

    class CenteredButton(context: Context): ContourLayout(context) {
        init {
            background = PaintDrawable(COLORS[1])
            Button(context).apply {
                text = DUMMY_BUTTON_TEXT
                layoutBy(
                    x = centerHorizontallyTo { parent.centerX() }.widthOf { BUTTON_WIDTH.xdip },
                    y = centerVerticallyTo { parent.centerY() }.heightOf { BUTTON_HEIGHT.ydip }
                )
            }
        }
    }
}