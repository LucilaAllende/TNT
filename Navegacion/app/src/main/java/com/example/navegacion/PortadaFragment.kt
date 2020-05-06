package com.example.navegacion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.navegacion.databinding.FragmentPortadaBinding

/**
 * A simple [Fragment] subclass.
 */
class PortadaFragment : Fragment() {

    private var _binding: FragmentPortadaBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPortadaBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.startButton.setOnClickListener { startClicked() }
        return view
    }

    private fun startClicked() {
        Log.i("JuegoFragment", "Click en el boton")

        // TODO #6.2: Agregar animacio|nes
        // solicitamos la clase especial NavController.
        // Podemos pasar un destino o una acción.
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
