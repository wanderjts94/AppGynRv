package pe.edu.idat.appgynrv

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import pe.edu.idat.appgynrv.Retrofit.models.Tienda.Tienda
import pe.edu.idat.appgynrv.Retrofit.services.tiendaservice
import pe.edu.idat.appgynrv.databinding.FragmentTiendaBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TiendaFragment : Fragment() {
    private var _binding: FragmentTiendaBinding? = null
    private val binding get() = _binding!!
    private lateinit var tiendaService: tiendaservice
    private var categoria: String = ""
    private lateinit var adapterTienda: AdapterTienda

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTiendaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar adaptador para RecyclerView de productos
        adapterTienda = AdapterTienda(mutableListOf(), requireContext())
        binding.rvProductos.layoutManager = GridLayoutManager(context, 2)
        binding.rvProductos.adapter = adapterTienda

        // Inicializar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:9090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Crear instancia de la interfaz tiendaservice
        tiendaService = retrofit.create(tiendaservice::class.java)

        // Manejar clics en botones para cambiar visibilidad de RecyclerViews y obtener productos
        binding.btnsuplemento.setOnClickListener {
            obtenerProductos("Suplementos")
            Log.i("Datos de ObtenerProductos(Suplementos)", obtenerProductos("Suplementos").toString())
        }

        binding.btnequipoentre.setOnClickListener {
            obtenerProductos("Entrenamiento")
            Log.i("Datos de ObtenerProductos(Entrenamiento)", obtenerProductos("Entrenamiento").toString())
        }

        binding.btnotros.setOnClickListener {
            obtenerProductos("Otros")
            Log.i("Datos de ObtenerProductos(Otros)", obtenerProductos("Otros").toString())
        }
    }

    private fun obtenerProductos(categoria: String) {
        // Llamar al método obtenerListaProductos con la categoría seleccionada
        val call = tiendaService.obtenerListaProductos(categoria)
        call.enqueue(object : Callback<List<Tienda>> {
            override fun onResponse(call: Call<List<Tienda>>, response: Response<List<Tienda>>) {
                if (response.isSuccessful) {
                    // La respuesta fue exitosa
                    val listaProductos = response.body()
                    if (listaProductos != null) {
                        // Actualizar la lista de productos en el adaptador
                        adapterTienda.actualizarProductos(listaProductos)
                    }
                } else {
                    // La respuesta no fue exitosa
                    Log.e("Response", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Tienda>>, t: Throwable) {
                // Se produjo un error en la solicitud
                Log.e("Failure", "Error: ${t.message}")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}