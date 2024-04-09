package pe.edu.idat.appgynrv.Views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.Retrofit.models.Tienda.Tienda
import pe.edu.idat.appgynrv.Retrofit.services.tiendaservice
import pe.edu.idat.appgynrv.Views.Adapters.AdapterTienda
import pe.edu.idat.appgynrv.databinding.FragmentTiendaBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TiendaFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentTiendaBinding? = null
    private val binding get() = _binding!!
    private lateinit var tiendaService: tiendaservice
    private lateinit var adapterTienda: AdapterTienda

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTiendaBinding.inflate(inflater, container, false)
        val view = binding.root

        // Configurar adaptador para RecyclerView de productos
        adapterTienda = AdapterTienda(emptyList(), requireContext())
        binding.rvProductos.layoutManager = GridLayoutManager(context, 1)
        binding.rvProductos.adapter = adapterTienda

        // Inicializar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.21:9090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding.btnsuplemento.setOnClickListener(this)
        binding.btnequipoentre.setOnClickListener(this)
        binding.btnotros.setOnClickListener(this)

        binding.btncarrito.setOnClickListener{
            findNavController().navigate(R.id.carritoCompraFragment)
        }
        // Crear instancia de la interfaz tiendaservice
        tiendaService = retrofit.create(tiendaservice::class.java)
        getProductosxCategoria("Suplementos")
        return view
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnsuplemento -> getProductosxCategoria("Suplementos")
            R.id.btnequipoentre -> getProductosxCategoria("Entrenamiento")
            R.id.btnotros -> getProductosxCategoria("Otros")
        }
    }

    fun getProductosxCategoria(categoria: String) {
        tiendaService.obtenerListaProductos(categoria)
            .enqueue(object : Callback<List<Tienda>> {
                override fun onResponse(
                    call: Call<List<Tienda>>,
                    response: Response<List<Tienda>>
                ) {
                    if (response.isSuccessful) {
                        val listaproductos = response.body()
                        listaproductos?.let {
                            // Actualiza el adaptador con la lista de productos obtenida
                            adapterTienda.actualizarProductos(it)
                        }
                        Log.i("Valores de listaproductos", listaproductos.toString())
                        Log.i("Fragmento", binding.rvProductos.toString())
                    } else {
                        Log.e(
                            "Error en la tienda",
                            "Error al obtener los productos de la categoria $categoria, lista obtenida: ${response}"
                        )
                    }
                }

                override fun onFailure(call: Call<List<Tienda>>, t: Throwable) {
                    Log.e("Mensaje de error", t.message.toString())
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
