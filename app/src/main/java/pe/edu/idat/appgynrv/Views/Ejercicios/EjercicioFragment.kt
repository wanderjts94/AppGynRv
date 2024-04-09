package pe.edu.idat.appgynrv.Views.Ejercicios

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.CalendarioFragment
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.Views.Adapters.AdapterRutina
import pe.edu.idat.appgynrv.Views.Rutina.Rutina
import pe.edu.idat.appgynrv.databinding.FragmentEjercicioBinding

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
        val view = binding.root
        val listaEjercicios = obtenerEjercicios()

        binding.rvlistaejer.layoutManager=LinearLayoutManager(context)
        binding.rvlistaejer.adapter= AdapterRutina(listaEjercicios,requireContext())
        //
        binding.btnhaztepremium.setOnClickListener{
            val navController = it.findNavController()
            navController.navigate(R.id.haztePremiumFragment)
        }

        binding.btnconfiguracion.setOnClickListener{
            val navController = it.findNavController()
            navController.navigate(R.id.calendarioFragment)
        }

        return view
    }


    private fun obtenerEjercicios():List<Rutina>{
        val listaEjercicios = ArrayList<Rutina>()
        listaEjercicios.add(Rutina("Básico",R.drawable.princi))
        listaEjercicios.add(Rutina("Medio",R.drawable.avanzado))
        listaEjercicios.add(Rutina("Avanzado",R.drawable.medio))
        return listaEjercicios
    }

}