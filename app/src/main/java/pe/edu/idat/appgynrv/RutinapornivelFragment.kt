package pe.edu.idat.appgynrv

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity

import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.Retrofit.models.ejercicios.getlistaejercicioResponse
import pe.edu.idat.appgynrv.Retrofit.services.ejercicioservice
import pe.edu.idat.appgynrv.databinding.FragmentRutinapornivelBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RutinapornivelFragment : Fragment() {
    private var _binding: FragmentRutinapornivelBinding? = null
    private val binding get() = _binding!!

    private lateinit var ejerciciosService: ejercicioservice
    private lateinit var adapter: AdapterNivelRutina

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

        _binding = FragmentRutinapornivelBinding.inflate(inflater, container, false)
        val view = binding.root


        // Inicializar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.10:9090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Inicializar el servicio de ejercicios
        ejerciciosService = retrofit.create(ejercicioservice::class.java)

        // Configurar RecyclerView
        adapter = AdapterNivelRutina(emptyList(), requireContext())
        binding.rvlistaejerciciosR.layoutManager = LinearLayoutManager(context)
        binding.rvlistaejerciciosR.adapter = adapter

        // Realizar la solicitud para obtener la lista de ejercicios
        ejerciciosService.obtenerListaEjercicios().enqueue(object : Callback<getlistaejercicioResponse> {
            override fun onResponse(call: Call<getlistaejercicioResponse>, response: Response<getlistaejercicioResponse>) {
                if (response.isSuccessful) {
                    val listaEjercicios = response.body()?.ejercicios
                    listaEjercicios?.let {
                        Log.d("API_Response", "Lista de ejercicios obtenida correctamente: $it")
                        adapter.actualizarLista(it)
                    }
                } else {
                    Log.d("API_Response", "Error al obtener la lista de ejercicios. Código de error: ${response.code()}")
                    Toast.makeText(requireContext(), "Error al obtener la lista de ejercicios", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<getlistaejercicioResponse>, t: Throwable) {
                Log.e("API_Request", "Error de conexión: ${t.message}")
                Toast.makeText(requireContext(), "Error de conexión. Por favor, inténtalo de nuevo más tarde.", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}