package pe.edu.idat.appgynrv

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import pe.edu.idat.appgynrv.Retrofit.models.Perfil.getperfilResponse
import pe.edu.idat.appgynrv.Retrofit.services.pefilservice
import pe.edu.idat.appgynrv.databinding.FragmentInformeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InformeFragment : Fragment() {
    private lateinit var binding: FragmentInformeBinding
    private lateinit var correoUsuario: String
    private lateinit var perfilService: pefilservice

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtener el correo electrónico del usuario desde SharedPreferences
        obtenerCorreoUsuario()
        // Configurar Retrofit
        configurarRetrofit()
        // Obtener perfil del usuario usando el correo electrónico
        obtenerPerfilUsuario()
    }

    private fun obtenerCorreoUsuario() {
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        correoUsuario = sharedPreferences.getString("correo", "") ?: ""
    }

    private fun configurarRetrofit() {
        // Configuración de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.10:9090/api/usuarios/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        perfilService = retrofit.create(pefilservice::class.java)
    }

    private fun obtenerPerfilUsuario() {
        // Llamar al servicio para obtener el perfil del usuario
        val call = perfilService.obtenerPerfilPorCorreo(correoUsuario)
        call.enqueue(object : Callback<getperfilResponse> {
            override fun onResponse(call: Call<getperfilResponse>, response: Response<getperfilResponse>) {
                if (response.isSuccessful) {
                    val perfil = response.body()
                    perfil?.let {
                        // Una vez obtenido el perfil, obtener el peso y la altura
                        val peso = it.peso
                        val altura = it.altura
                        // Actualizar la UI con el peso, la altura, el peso ideal y el estado del usuario
                        actualizarCamposUI(peso, altura)
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al obtener perfil del usuario", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<getperfilResponse>, t: Throwable) {
                Log.e("PerfilFragment", "Error al obtener perfil del usuario", t)
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun calcularPesoIdeal(altura: Double): Pair<Double, Double> {
        // Calcular el rango de peso ideal (18.5 - 24.9 es considerado saludable)
        val pesoMinimoIdeal = 18.5 * altura * altura
        val pesoMaximoIdeal = 24.9 * altura * altura
        return Pair(pesoMinimoIdeal, pesoMaximoIdeal)
    }

    private fun calcularEstadoUsuario(peso: Double, altura: Double): String {
        // Calcular el IMC
        val imc = peso / (altura * altura)
        // Determinar el estado del usuario según su IMC
        return when {
            imc < 18.5 -> "Delgado"
            imc >= 18.5 && imc < 24.9 -> "Normal"
            imc >= 24.9 && imc < 29.9 -> "Sobrepeso"
            else -> "Obeso"
        }
    }

    private fun actualizarCamposUI(peso: Double, altura: Double) {
        // Establecer los valores en los campos de entrada de altura y peso
        binding.etaltura.setText(String.format("%.2f", altura))
        binding.etPeso.setText(String.format("%.2f", peso))
        // Calcular y mostrar el peso ideal
        val (pesoMinimoIdeal, pesoMaximoIdeal) = calcularPesoIdeal(altura)
        binding.textViewGrafico.text = "Peso ideal según la talla: Entre ${String.format("%.2f", pesoMinimoIdeal)} y ${String.format("%.2f", pesoMaximoIdeal)} kg"
        // Calcular y mostrar el estado del usuario
        val estadoUsuario = calcularEstadoUsuario(peso, altura)
        binding.textViewEstado.text = "Estado del usuario: $estadoUsuario"
    }
}
