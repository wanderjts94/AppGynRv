package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.databinding.FragmentCarritoCompraBinding



class CarritoCompraFragment : Fragment() {
    private var _binding: FragmentCarritoCompraBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentCarritoCompraBinding.inflate(inflater,container,false)
        val view= binding.root
        val listadeproductos = obtenerproductos()
        binding.rvcarritotien.layoutManager= LinearLayoutManager(context)
        binding.rvcarritotien.addItemDecoration(VerticalSpaceItemDecoration(16)) // Espacio vertical entre elementos
        binding.rvcarritotien.adapter=AdapterCarrito(listadeproductos,requireContext())
        return view
    }

    private fun obtenerproductos(): List<Datoscarrito>{
        val listaprodutos = ArrayList<Datoscarrito>()
        listaprodutos.add(Datoscarrito("proteinas","5.00",2,R.drawable.ima1))
        listaprodutos.add(Datoscarrito("mancuernas","6.00",5,R.drawable.ima2))
        listaprodutos.add(Datoscarrito("esporade","5.00",7,R.drawable.ima1))
        listaprodutos.add(Datoscarrito("polo","6.00",9,R.drawable.ima2))

        return  listaprodutos
    }



}