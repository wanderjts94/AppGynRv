package pe.edu.idat.appgynrv.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.databinding.FragmentFelicidadesBinding


class FelicidadesFragment : Fragment() {
    private var _binding: FragmentFelicidadesBinding? = null
    private val binding get() = _binding!! // Accede al binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar y obtener la instancia del binding
        _binding = FragmentFelicidadesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar OnClickListener para el botón
        binding.btnvolverejer.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.ejercicioFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Liberar la referencia al binding cuando la vista se destruye
    }
}