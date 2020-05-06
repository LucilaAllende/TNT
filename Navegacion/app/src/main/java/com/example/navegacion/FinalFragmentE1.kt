package com.example.navegacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class FinalFragmentE1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_final_e1, container, false)
    }


    /*val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
        // Handle the back button event
    }*/


}
