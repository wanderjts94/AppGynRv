package pe.edu.idat.appgynrv.Subinterfaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.databinding.FragmentPreFelicidadesEjer4Binding


class PreFelicidadesEjer4Fragment : Fragment() {
    private var _binding:FragmentPreFelicidadesEjer4Binding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pre_felicidades_ejer4, container, false)
        _binding= FragmentPreFelicidadesEjer4Binding.inflate(inflater,container,false)
        val view= binding.root
        binding.btnSiguiente4.setOnClickListener{
            // Obtener el nombre y número de repeticiones del segundo ejercicio
            val nombreEjercicio5 = "Estiramiento de Pecho"
            val numRepeticiones5 = 9 // Por ejemplo, obtén el número de repeticiones de algún lugar

            // Crear un Bundle para pasar los argumentos al fragmento EjercicioProceso2Fragment
            val args = Bundle().apply {
                putString("nombreEjercicio5", nombreEjercicio5)
                putInt("numRepeticiones5", numRepeticiones5)
            }
            // Navegar al fragmento EjercicioProceso2Fragment con los argumentos
            findNavController().navigate(R.id.estiramientoPechoProcesoFragment,args)
        }
        return view
    }

}