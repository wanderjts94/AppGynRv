package pe.edu.idat.appgynrv


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.appgynrv.Retrofit.models.ejercicios.Ejercicio
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
        _binding = FragmentRutinapornivelBinding.inflate(inflater, container, false)
        val view = binding.root

        // Obtener el correo electrónico almacenado en SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val nombre = sharedPreferences.getString("nombre", "")

        // Inicializar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.3:9090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Inicializar el servicio de ejercicios
        ejerciciosService = retrofit.create(ejercicioservice::class.java)

        // Configurar RecyclerView
        adapter = AdapterNivelRutina(emptyList(), requireContext())
        binding.rvlistaejerciciosR.layoutManager = LinearLayoutManager(context)
        binding.rvlistaejerciciosR.adapter = adapter
        binding.etnamerutina.text="Nivel " + nombre!!

        binding.btnInicio.setOnClickListener {
            val nombreEjercicio: String
            val numRepeticiones: Int

            when (nombre) {
                "Básico" -> {
                    nombreEjercicio = "Flexiones con Inclinacion"
                    numRepeticiones = 10
                }
                "Medio" -> {
                    nombreEjercicio = "Flexiones con Inclinacion"
                    numRepeticiones = 12
                }
                "Avanzado" -> {
                    nombreEjercicio = "Flexiones con Inclinacion"
                    numRepeticiones = 15
                }
                else -> {
                    nombreEjercicio = ""
                    numRepeticiones = 0
                }
            }

            // Crear un Bundle para pasar los argumentos
            val args = Bundle()
            args.putString("nombreEjercicio", nombreEjercicio)
            args.putInt("numRepeticiones", numRepeticiones)

            // Navegar al fragmento EjercicioProcesoFragment con los argumentos
            findNavController().navigate(R.id.ejercicioProcesoFragment, args)
        }

        ejerciciosService.obtenerListaEjercicios(nombre).enqueue(object : Callback<List<Ejercicio>> {
            override fun onResponse(call: Call<List<Ejercicio>>, response: Response<List<Ejercicio>>) {
                if (response.isSuccessful) {
                    val listaEjercicios = response.body()
                    listaEjercicios?.let {
                        adapter.actualizarLista(it)
                    }
                    Log.i("Lista de ejercicio", listaEjercicios.toString())
                    Log.i("Url completo", ejerciciosService.obtenerListaEjercicios(nombre).toString())
                } else {
                    Toast.makeText(requireContext(), "Error al obtener la lista de ejercicios", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Ejercicio>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión. Por favor, inténtalo de nuevo más tarde.", Toast.LENGTH_SHORT).show()
                Log.e("Mensaje de error", t.message.toString())
                Log.i("Nombre de la Rutina Seleccionada", nombre.toString())
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}