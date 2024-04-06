package pe.edu.idat.appgynrv

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.databinding.FragmentCarritoCompraBinding

class CarritoCompraFragment : Fragment() {


    private var _binding: FragmentCarritoCompraBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AdapterCarrito

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarritoCompraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvcarritotien.layoutManager = LinearLayoutManager(context)
        adapter = AdapterCarrito(requireContext(), this)
        binding.rvcarritotien.adapter = adapter
        binding.btnimgcarritoc.setOnClickListener {
            findNavController().navigate(R.id.tiendaFragment)
        }
        binding.btnpagar.setOnClickListener {
            val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("datosparacarrito") // Eliminar la entrada "datosparacarrito" de SharedPreferences
            editor.apply() // Aplicar los cambios
            adapter.actualizarProductos(emptyList()) // Limpiar la lista de productos en el adaptador
            calcularTotal()
        }
        // Obtener y mostrar los productos del carrito
        mostrarProductosEnCarrito()
    }

    private fun mostrarProductosEnCarrito() {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val datosCarrito = sharedPreferences.getString("datosparacarrito", "")

        if (!datosCarrito.isNullOrEmpty()) {
            Log.d("CarritoCompraFragment", "Datos guardados en SharedPreferences (datosparacarrito): $datosCarrito")
            val productos = datosCarrito.split(";")
            Log.d("listaproducto", "los productos: $productos")
            val listaDeProductos = mutableListOf<Datoscarrito>()

            for (productoData in productos) {
                val dataArray = productoData.split(",")
                if (dataArray.size >= 4) {
                    val producto = Datoscarrito(dataArray[1], dataArray[2], dataArray[3], dataArray[0].toInt())
                    listaDeProductos.add(producto)
                    Log.d("listaproducto", "los lista productos): $listaDeProductos")
                }
            }
            adapter.actualizarProductos(listaDeProductos)
        }
    }

    // Función para calcular y actualizar el total del carrito
    fun calcularTotal() {
        var total = 0.0
        for (producto in adapter.listaDeProductos) {
            val precioProducto = producto.precio.toDoubleOrNull()
            val cantidadProducto = producto.cantidad.toIntOrNull()
            if (precioProducto != null && cantidadProducto != null) {
                total += precioProducto * cantidadProducto
            }
        }
        // Actualizar el TextView que muestra el total
        binding.tvtotpagar.text = total.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}