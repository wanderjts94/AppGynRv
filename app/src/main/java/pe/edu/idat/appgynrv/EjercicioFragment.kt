package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.databinding.FragmentEjercicioBinding
import java.util.ArrayList

class EjercicioFragment : Fragment() {
    private var _binding:FragmentEjercicioBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_ejercicio, container, false)
        _binding= FragmentEjercicioBinding.inflate(inflater,container,false)
        val view= binding.root
        val listaEjercicios = obtenerEjercicios()
        binding.rvlistejer.layoutManager= LinearLayoutManager(context)
        binding.rvlistejer.adapter=AdapterRutinas(listaEjercicios,requireContext())
        return view
    }

    private fun obtenerEjercicios(): List<Rutinas>{
        val listaEjercicios = ArrayList<Rutinas>()
        listaEjercicios.add(Rutinas("Rutina para Principiantes",R.drawable.princi))
        listaEjercicios.add(Rutinas("Rutina para Intermedios",R.drawable.avanzado))
        listaEjercicios.add(Rutinas("Rutina para Avanzados",R.drawable.medio))
        listaEjercicios.add(Rutinas("Premium",R.drawable.premium01))

        return  listaEjercicios
    }

}