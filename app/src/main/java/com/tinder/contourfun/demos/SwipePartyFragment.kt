package com.tinder.contourfun.demos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tinder.contourfun.swipeparty.SwipePartyPanel

/* only for demo */
class SwipePartyFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = SwipePartyPanel(requireContext())
}