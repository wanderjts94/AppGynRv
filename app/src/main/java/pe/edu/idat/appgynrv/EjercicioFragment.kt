package pe.edu.idat.appgynrv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.rvlistaejer.adapter=AdapterRutina(listaEjercicios,requireContext())
        //
        binding.btnhaztepremium.setOnClickListener{
            val navController = it.findNavController()
            navController.navigate(R.id.haztePremiumFragment)
        }
        return view
    }

    private fun obtenerEjercicios():List<Rutina>{
        val listaEjercicios = ArrayList<Rutina>()
        listaEjercicios.add(Rutina("Rutina para Principiantes",R.drawable.princi))
        listaEjercicios.add(Rutina("Rutina para Intermedios",R.drawable.avanzado))
        listaEjercicios.add(Rutina("Rutina para Avanzados",R.drawable.medio))
        return listaEjercicios
    }

}