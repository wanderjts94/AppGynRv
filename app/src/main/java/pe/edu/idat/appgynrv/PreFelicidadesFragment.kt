package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.databinding.FragmentPreFelicidadesBinding


class PreFelicidadesFragment : Fragment() {
    private var _binding:FragmentPreFelicidadesBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pre_felicidades, container, false)
        _binding= FragmentPreFelicidadesBinding.inflate(inflater,container,false)
        val view= binding.root
        // Inflar el diseño del fragmento y otros códigos necesarios
        binding.btnSiguiente.setOnClickListener{
            // Obtener el nombre y número de repeticiones del segundo ejercicio
            val nombreEjercicio2 = "Flexiones con Estiramiento"
            val numRepeticiones2 = 5 // Por ejemplo, obtén el número de repeticiones de algún lugar

            // Crear un Bundle para pasar los argumentos al fragmento EjercicioProceso2Fragment
            val args = Bundle().apply {
                putString("nombreEjercicio2", nombreEjercicio2)
                putInt("numRepeticiones2", numRepeticiones2)
            }
            // Navegar al fragmento EjercicioProceso2Fragment con los argumentos
            findNavController().navigate(R.id.flexionesEstiramientoProcesoFragment,args)
        }
        return view
    }


}