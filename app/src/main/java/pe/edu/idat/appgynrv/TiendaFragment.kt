package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.databinding.FragmentTiendaBinding

class TiendaFragment : Fragment() {
    private var _binding:FragmentTiendaBinding?=null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_tienda, container, false)
        _binding=FragmentTiendaBinding.inflate(inflater,container,false)
        val view= binding.root
        val listaItemTienda =obtenerItemTienda()
        binding.rvitemproductos.layoutManager= GridLayoutManager(context,2)
        binding.rvitemproductos.adapter=AdapterTienda(listaItemTienda,requireContext())
        return view
    }

    private fun obtenerItemTienda():List<Tienda>{
        val listaItemTienda = ArrayList<Tienda>()
        listaItemTienda.add(Tienda(R.drawable.ima1,"Producto:Vitamina Embace ","Precio:15","vitamina para agarrar masa"))
        listaItemTienda.add(Tienda(R.drawable.ima1,"Producto:Vitamina Embace ","Precio:20","vitamina para agarrar masa"))
        return listaItemTienda
    }
}