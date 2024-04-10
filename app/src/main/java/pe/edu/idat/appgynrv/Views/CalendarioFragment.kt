package pe.edu.idat.appgynrv

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.idat.appgynrv.Retrofit.models.Config.postConfigRequest
import pe.edu.idat.appgynrv.Retrofit.models.Config.postConfigResponse
import pe.edu.idat.appgynrv.Retrofit.services.configservice
import pe.edu.idat.appgynrv.databinding.FragmentCalendarioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CalendarioFragment : Fragment() {
    private var _binding: FragmentCalendarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var retrofit: Retrofit
    private lateinit var configService: configservice
    private var lastClickedDay = 0 // Variable para almacenar el último día clickeado

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarioBinding.inflate(inflater, container, false)
        val view = binding.root

        // Configuración de Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.43:9090/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Crear instancia del servicio
        configService = retrofit.create(configservice::class.java)
        // Asignar OnClickListener a los botones
        setOnClickListeners()
        // Agregar listener al botón "GUARDAR CAMBIOS"
        binding.btnlogin.setOnClickListener {
            // Obtener valores de los botones y del EditText
            val diasEntrenamiento = obtenerDiasEntrenamientoSeleccionados()
            val fechaInicio = binding.editTextFecha.text.toString()

            // Crear la solicitud para enviar al servicio
            val request = postConfigRequest(diasEntrenamiento, fechaInicio)

            // Llamar al servicio para crear la configuración
            configService.crearConfiguracion(request).enqueue(object : Callback<postConfigResponse> {
                override fun onResponse(call: Call<postConfigResponse>, response: Response<postConfigResponse>) {
                    if (response.isSuccessful) {
                        // Manejar respuesta exitosa
                        Log.i("Mensaje", response.body().toString())
                        Toast.makeText(requireContext(), "Se ha creado la configuración correctamente", Toast.LENGTH_SHORT).show()
                        binding.editTextFecha.setText("")
                    } else {
                        // Manejar respuesta no exitosa
                    }
                }

                override fun onFailure(call: Call<postConfigResponse>, t: Throwable) {
                    Log.e("ErrorCalendario", t.message.toString())
                }
            })
        }

        return view
    }

    private fun setOnClickListeners() {
        val buttons = listOf(binding.btn1, binding.btn2, binding.btn3, binding.btn4, binding.btn5, binding.btn6, binding.btn7)

        buttons.forEach { button ->
            button.setOnClickListener {
                lastClickedDay = button.text.toString().toInt() // Almacenar el día del botón clickeado
                Log.d("LastClickedDay", "El último día clickeado es: $lastClickedDay") // Registro en el Log
                // Aquí puedes realizar cualquier otra acción que desees cuando se presione un botón
            }
        }
    }

    private fun obtenerDiasEntrenamientoSeleccionados(): Int {
        return lastClickedDay // Retornar el último día clickeado
    }
}