package com.tinder.contourfun.demos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.contour.ContourLayout
import com.tinder.contourfun.quadwonderpanel.WonderPanel

class SnapToEdgeDemoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = WonderPanel(requireContext())

    class SnapToEdgeDemo(context: Context): ContourLayout(context) {

    }
}