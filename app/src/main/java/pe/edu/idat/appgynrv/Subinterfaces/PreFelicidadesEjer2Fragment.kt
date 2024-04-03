package pe.edu.idat.appgynrv.Subinterfaces

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.databinding.FragmentPreFelicidadesEjer2Binding

class PreFelicidadesEjer2Fragment : Fragment() {
    private var _binding:FragmentPreFelicidadesEjer2Binding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pre_felicidades_ejer2, container, false)
        _binding= FragmentPreFelicidadesEjer2Binding.inflate(inflater,container,false)
        val view= binding.root
        binding.btnSiguiente2.setOnClickListener{
            // Obtener el nombre y número de repeticiones del segundo ejercicio
            val nombreEjercicio3 = "Salto en Tijeras"
            val numRepeticiones3 = 8 // Por ejemplo, obtén el número de repeticiones de algún lugar

            // Crear un Bundle para pasar los argumentos al fragmento EjercicioProceso2Fragment
            val args = Bundle().apply {
                putString("nombreEjercicio3", nombreEjercicio3)
                putInt("numRepeticiones3", numRepeticiones3)
            }
            // Navegar al fragmento EjercicioProceso2Fragment con los argumentos
            findNavController().navigate(R.id.saltoEnTijeraProcesoFragment,args)
        }

        return view

    }

}