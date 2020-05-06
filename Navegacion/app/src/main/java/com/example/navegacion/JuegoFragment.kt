package com.example.navegacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navegacion.databinding.FragmentJuegoBinding

/**
 * A simple [Fragment] subclass.
 */
class JuegoFragment : Fragment() {

    private var _binding: FragmentJuegoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var puntajeE1: Int = 10
    private var puntajeE2: Int = 10

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJuegoBinding.inflate(inflater, container, false)
        val vista = binding.root
        binding.countdow1Button.setOnClickListener {disminuirEq1()}
        binding.countdow2Button.setOnClickListener{disminuirEq2()}

        return vista
    }

    private fun disminuirEq1() {
        puntajeE1 -= 1
        binding.countdow1TextView.text = puntajeE1.toString()
        if (puntajeE1 == 0){
            binding.countdow1Button.isEnabled=false
            binding.juegoButton.visibility = View.VISIBLE
            binding.juegoButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_juegoFragment_to_finalFragmentE1, null)
            )
        }
    }

    private fun disminuirEq2(){
        puntajeE2 -= 1
        binding.countdow2TextView.text= puntajeE2.toString()
        if (puntajeE2 == 0){
            binding.countdow2Button.isEnabled=false
            binding.juegoButton.visibility = View.VISIBLE
            binding.juegoButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_juegoFragment_to_finalFragmentE2, null)
            )
        }
    }
}
