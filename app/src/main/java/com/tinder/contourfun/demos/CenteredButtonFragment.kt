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
                    x = centerHorizontallyTo { parent.centerX() }.widthOf { 174.xdip },
                    y = centerVerticallyTo { parent.centerY() }.heightOf { 54.ydip }
                )
            }
        }
    }
}