package pe.edu.idat.appgynrv.Views.Ejercicios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.databinding.FragmentInfosaltoTBinding

class InfosaltoTFragment : Fragment() {

    private lateinit var binding: FragmentInfosaltoTBinding
    private var isImage1Displayed = true // Variable para controlar qué imagen se muestra

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using Data Binding
        binding = FragmentInfosaltoTBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar OnClickListener para el botón SIGUIENTE
        binding.btnSiguiente.setOnClickListener {
            // Navegar hacia el fragmento InfoFlexDFragment
            findNavController().navigate(R.id.action_infosaltoTFragment_to_infoFlexDFragment)
        }

        // Configurar OnClickListener para el botón CERRAR
        binding.btncerrar.setOnClickListener {
            // Navegar hacia el fragmento RutinapornivelFragment
            findNavController().navigate(R.id.to_rutinapornivelFragment)
        }

        // Configurar OnClickListener para el botón ANIMACIÓN
        binding.btnAnimacion.setOnClickListener {
            // Cambiar la imagen de acuerdo al estado actual
            if (isImage1Displayed) {
                binding.imageView.setImageResource(R.drawable.ejerciciosv2)
            } else {
                binding.imageView.setImageResource(R.drawable.carga2)
            }
            // Cambiar el estado de la variable
            isImage1Displayed = !isImage1Displayed
        }

        // Configurar OnClickListener para el botón TUTORIAL
        binding.btnTutorial.setOnClickListener {
            // Restaurar la imagen original
            binding.imageView.setImageResource(R.drawable.ejerciciosv2)
            // Actualizar el estado de la variable
            isImage1Displayed = true
        }
    }
}