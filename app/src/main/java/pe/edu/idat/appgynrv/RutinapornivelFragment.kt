package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.databinding.FragmentRutinapornivelBinding


class RutinapornivelFragment : Fragment() {
    private var _binding:FragmentRutinapornivelBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_ejercicio, container, false)
        _binding= FragmentRutinapornivelBinding.inflate(inflater,container,false)
        // Obtener el correo electrónico almacenado en SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val nombre = sharedPreferences.getString("nombre", "")

        val view= binding.root
        val listanivelEjercicios = obtenerEjercicios()
        binding.rvlistaejerciciosR.layoutManager= LinearLayoutManager(context)
        binding.rvlistaejerciciosR.addItemDecoration(VerticalSpaceItemDecoration(16)) // Espacio vertical entre elementos
        binding.rvlistaejerciciosR.adapter=AdapterNivelRutina(listanivelEjercicios,requireContext())
        binding.etnamerutina.text= nombre
        return view
    }

    private fun obtenerEjercicios(): List<NivelRutinas>{
        val listaEjercicios = ArrayList<NivelRutinas>()
        listaEjercicios.add(NivelRutinas("salto tijera","5",R.drawable.ima1))
        listaEjercicios.add(NivelRutinas("planchas","6",R.drawable.ima2))
        listaEjercicios.add(NivelRutinas("salto tijera","5",R.drawable.ima1))
        listaEjercicios.add(NivelRutinas("planchas","6",R.drawable.ima2))

        return  listaEjercicios
    }



}