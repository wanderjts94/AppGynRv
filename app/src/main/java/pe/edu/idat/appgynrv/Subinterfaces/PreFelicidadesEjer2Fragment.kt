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
import androidx.appcompat.app.AppCompatActivity
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

        // Obtener el correo electrónico almacenado en SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val nombre = sharedPreferences.getString("nombre", "")

        binding.btnSiguiente2.setOnClickListener {
            // Obtener el nombre y número de repeticiones del tercer ejercicio
            val nombreEjercicio3: String
            val numRepeticiones3: Int

            when (nombre) {
                "Básico" -> {
                    nombreEjercicio3 = "Estiramiento de Cobra"
                    numRepeticiones3 = 10
                }
                "Medio" -> {
                    nombreEjercicio3 = "Estiramiento de Cobra"
                    numRepeticiones3 = 12
                }
                "Avanzado" -> {
                    nombreEjercicio3 = "Estiramiento de Cobra"
                    numRepeticiones3 = 15
                }
                else -> {
                    nombreEjercicio3 = ""
                    numRepeticiones3 = 0
                }
            }

            // Crear un Bundle para pasar los argumentos al fragmento correspondiente
            val args = Bundle().apply {
                putString("nombreEjercicio3", nombreEjercicio3)
                putInt("numRepeticiones3", numRepeticiones3)
            }
            // Navegar al fragmento correspondiente con los argumentos
            findNavController().navigate(R.id.saltoEnTijeraProcesoFragment, args)
        }

        return view

    }

}