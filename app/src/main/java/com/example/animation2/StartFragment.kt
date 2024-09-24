package com.example.animation2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction


class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        val logoIV: ImageView = view.findViewById(R.id.logoIV)
        val startBT: Button = view.findViewById(R.id.startBT)
        startBT.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            val shopFragment = ShopFragment()
            transaction?.replace(R.id.fragment_container, ShopFragment())
            transaction?.addToBackStack(null)
            transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction?.commit()
        }
        return view
    }

}