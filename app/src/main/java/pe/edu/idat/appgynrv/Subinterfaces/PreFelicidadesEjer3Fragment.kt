package pe.edu.idat.appgynrv.Subinterfaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.databinding.FragmentPreFelicidadesEjer3Binding


class PreFelicidadesEjer3Fragment : Fragment() {
    private var _binding: FragmentPreFelicidadesEjer3Binding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pre_felicidades_ejer3, container, false)
        _binding=FragmentPreFelicidadesEjer3Binding.inflate(inflater,container,false)
        val view= binding.root
        binding.btnSiguiente3.setOnClickListener{
            // Obtener el nombre y número de repeticiones del segundo ejercicio
            val nombreEjercicio4 = "Estiramiento de Cobra"
            val numRepeticiones4 = 6 // Por ejemplo, obtén el número de repeticiones de algún lugar

            // Crear un Bundle para pasar los argumentos al fragmento EjercicioProceso2Fragment
            val args = Bundle().apply {
                putString("nombreEjercicio4", nombreEjercicio4)
                putInt("numRepeticiones4", numRepeticiones4)
            }
            // Navegar al fragmento EjercicioProceso2Fragment con los argumentos
            findNavController().navigate(R.id.estiramientoCobraProcesoFragment,args)
        }
        return view
    }


}