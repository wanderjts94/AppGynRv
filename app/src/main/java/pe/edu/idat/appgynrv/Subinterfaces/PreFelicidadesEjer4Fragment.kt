package pe.edu.idat.appgynrv.Subinterfaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

        // Obtener el correo electrónico almacenado en SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val nombre = sharedPreferences.getString("nombre", "")


        binding.btnSiguiente4.setOnClickListener {
            // Obtener el nombre y número de repeticiones del quinto ejercicio
            val nombreEjercicio5: String
            val numRepeticiones5: Int

            when (nombre) {
                "Básico" -> {
                    nombreEjercicio5 = "Salto de Tijeras"
                    numRepeticiones5 = 15
                }
                "Medio" -> {
                    nombreEjercicio5 = "Salto de Tijeras"
                    numRepeticiones5 = 20
                }
                "Avanzado" -> {
                    nombreEjercicio5 = "Salto de Tijeras"
                    numRepeticiones5 = 25
                }
                else -> {
                    nombreEjercicio5 = ""
                    numRepeticiones5 = 0
                }
            }

            // Crear un Bundle para pasar los argumentos al fragmento correspondiente
            val args = Bundle().apply {
                putString("nombreEjercicio5", nombreEjercicio5)
                putInt("numRepeticiones5", numRepeticiones5)
            }
            // Navegar al fragmento correspondiente con los argumentos
            findNavController().navigate(R.id.estiramientoPechoProcesoFragment, args)
        }

        return view
    }

}