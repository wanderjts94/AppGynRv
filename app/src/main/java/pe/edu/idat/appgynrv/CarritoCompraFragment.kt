package pe.edu.idat.appgynrv

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.databinding.FragmentCarritoCompraBinding

class CarritoCompraFragment : Fragment() {
    private var _binding: FragmentCarritoCompraBinding? = null
    private val binding get() = _binding!!

    // Lista global para almacenar productos en el carrito
    private val listaDeProductos = ArrayList<Datoscarrito>()
    private lateinit var adapter: AdapterCarrito

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        _binding = FragmentCarritoCompraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        binding.rvcarritotien.layoutManager = LinearLayoutManager(context)
        adapter = AdapterCarrito(listaDeProductos, requireContext())
        binding.rvcarritotien.adapter = adapter
        binding.btnimgcarritoc.setOnClickListener {
            findNavController().navigate(R.id.tiendaFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        // Limpiar la lista y agregar productos guardados de SharedPreferences cada vez que se reanude el fragmento
        listaDeProductos.clear()
        obtenerYAgregarProductos()
    }

    private fun obtenerYAgregarProductos() {
        // Obtener datos de SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val datosCarrito = sharedPreferences.getString("datosparacarrito", "")

        // Si hay datos guardados en SharedPreferences
        if (!datosCarrito.isNullOrEmpty()) {
            // Dividir los datos guardados
            val dataArray = datosCarrito.split(",")

            // Crear un nuevo objeto Datoscarrito
            val producto = Datoscarrito(dataArray[1], dataArray[2], dataArray[3], dataArray[0].toInt())

            // Agregar el nuevo producto a la lista global
            listaDeProductos.add(producto)

            // Notificar al adaptador que se ha agregado un nuevo producto
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}