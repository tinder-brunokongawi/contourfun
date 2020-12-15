package com.tinder.contourfun.demos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tinder.contourfun.rocketlaunch.RocketLaunchView

class RocketLaunchDemoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = RocketLaunchView(requireContext())
}