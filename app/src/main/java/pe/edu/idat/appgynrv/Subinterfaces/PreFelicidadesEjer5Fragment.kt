package pe.edu.idat.appgynrv.Subinterfaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.databinding.FragmentPreFelicidadesEjer5Binding


class PreFelicidadesEjer5Fragment : Fragment() {

    private var _binding:FragmentPreFelicidadesEjer5Binding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pre_felicidades_ejer5, container, false)
        _binding=FragmentPreFelicidadesEjer5Binding.inflate(inflater,container,false)
        val view= binding.root
        binding.btnSiguiente5.setOnClickListener{
            // Navegar al fragmento EjercicioProceso2Fragment con los argumentos
            findNavController().navigate(R.id.felicidadesFragment)
        }
        return view
    }


}