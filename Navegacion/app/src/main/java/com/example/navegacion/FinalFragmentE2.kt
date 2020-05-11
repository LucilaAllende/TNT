package com.example.navegacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.navegacion.databinding.FragmentFinalE2Binding

/**
 * A simple [Fragment] subclass.
 * Use the [FinalFragmentE2.newInstance] factory method to
 * create an instance of this fragment.
 */
class FinalFragmentE2 : Fragment() {

    private var _binding: FragmentFinalE2Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinalE2Binding.inflate(inflater, container, false)
        val view = binding.root
        binding.buttonJugarAgain.setOnClickListener { jugarAgain() }
        return view
    }

    private fun jugarAgain() {
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
        findNavController().navigate(R.id.juegoFragment, null, options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
