package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
        // Obtener el correo electrónico almacenado en SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val nombre = sharedPreferences.getString("nombre", "")

        binding.btnSiguiente.setOnClickListener {
            // Obtener el nombre y número de repeticiones del segundo ejercicio
            val nombreEjercicio2: String
            val numRepeticiones2: Int

            when (nombre) {
                "Básico" -> {
                    nombreEjercicio2 = "Flexiones "
                    numRepeticiones2 = 12
                }
                "Medio" -> {
                    nombreEjercicio2 = "Flexiones"
                    numRepeticiones2 = 15
                }
                "Avanzado" -> {
                    nombreEjercicio2 = "Flexiones"
                    numRepeticiones2 = 10
                }
                else -> {
                    nombreEjercicio2 = ""
                    numRepeticiones2 = 0
                }
            }

            // Crear un Bundle para pasar los argumentos al fragmento EjercicioProceso2Fragment
            val args = Bundle().apply {
                putString("nombreEjercicio2", nombreEjercicio2)
                putInt("numRepeticiones2", numRepeticiones2)
            }
            // Navegar al fragmento EjercicioProceso2Fragment con los argumentos
            findNavController().navigate(R.id.flexionesEstiramientoProcesoFragment, args)
        }
        return view
    }


}