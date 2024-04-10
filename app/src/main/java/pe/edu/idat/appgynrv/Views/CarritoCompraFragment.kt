package pe.edu.idat.appgynrv.Views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.Retrofit.models.Detalle.detalleRequest
import pe.edu.idat.appgynrv.Retrofit.models.Detalle.detalleResponse
import pe.edu.idat.appgynrv.Retrofit.services.detalleService
import pe.edu.idat.appgynrv.Views.Adapters.AdapterCarrito
import pe.edu.idat.appgynrv.databinding.FragmentCarritoCompraBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarritoCompraFragment : Fragment() {


    private var _binding: FragmentCarritoCompraBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AdapterCarrito
    private val detalleServicio: detalleService

    init {
        // Configuración de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.43:9090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        detalleServicio = retrofit.create(detalleService::class.java)
    }

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
            val datosCarrito = sharedPreferences.getString("datosparacarrito", "")
            val correo = sharedPreferences.getString("correo", "")
            val editor = sharedPreferences.edit()
            val total = binding.tvtotpagar.text.toString()
            editor.putString("total", total)
            editor.apply()


            if (!datosCarrito.isNullOrEmpty()) {
                val productos = datosCarrito.split(";")

                for (productoData in productos) {
                    val dataArray = productoData.split(",")
                    if (dataArray.size >= 4) {
                        val producto = Datoscarrito(dataArray[1], dataArray[2], dataArray[3], dataArray[0].toInt())
                        Log.d("Producto", "Nombre: ${producto.nombreProduct}, Precio: ${producto.precio}, Cantidad: ${producto.cantidad}")
                        val request = detalleRequest(producto.cantidad.toInt(), correo!!, producto.nombreProduct)
                        registrarDetalle(request)
                    }
                }
                val editor = sharedPreferences.edit()
                editor.remove("datosparacarrito") // Eliminar la entrada "datosparacarrito" de SharedPreferences
                editor.apply() // Aplicar los cambios
                adapter.actualizarProductos(emptyList()) // Limpiar la lista de productos en el adaptador
                calcularTotal()
                val navController = it.findNavController()
                navController.navigate(R.id.realizarPagoFragment)
            }
        }
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
                }
                Log.i("Subtotales", dataArray[1])
            }
            Log.d("listaproducto", "los lista productos: $listaDeProductos")
            Log.i("Datos Carrito", datosCarrito.toString())
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

    private fun registrarDetalle(detalleRequest: detalleRequest){
        detalleServicio.registrarDetalle(detalleRequest)
            .enqueue(object: Callback<detalleResponse>{
                override fun onResponse(
                    call: Call<detalleResponse>,
                    response: Response<detalleResponse>
                ) {
                    Log.i("Response", response.body().toString())
                }

                override fun onFailure(call: Call<detalleResponse>, t: Throwable) {
                    Log.e("ErrorRegistroDetalle", t.message.toString())
                }

            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}