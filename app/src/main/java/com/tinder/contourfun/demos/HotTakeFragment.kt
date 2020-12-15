package com.tinder.contourfun.demos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tinder.contourfun.hottake.HotTakeView

class HotTakeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): HotTakeView {
        val hotTakeView = HotTakeView(requireContext())
        hotTakeView.onClose = {
            //
        }
        return hotTakeView
    }
}