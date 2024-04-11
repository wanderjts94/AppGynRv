package pe.edu.idat.appgynrv.Views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.idat.appgynrv.Retrofit.models.Recuperacion.putRecuperarRequest
import pe.edu.idat.appgynrv.Retrofit.models.Recuperacion.putRecuperarResponse
import pe.edu.idat.appgynrv.Retrofit.services.recuperarservice
import pe.edu.idat.appgynrv.databinding.ActivityRecuperacionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class RecuperacionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecuperacionBinding
    private lateinit var recuperarService: recuperarservice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecuperacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.43:9090/api/usuarios/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Set click listener for tvlrecuperacion
        binding.tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        recuperarService = retrofit.create(recuperarservice::class.java)

        binding.btnguart.setOnClickListener {
            val correo = binding.etcorre.text.toString()
            val nuevaContra = binding.etnuevcontra.text.toString()
            val palabraClave = binding.etpalabra.text.toString()

            if (validatePassword(nuevaContra)) {
                val request = putRecuperarRequest(correo, nuevaContra, palabraClave)
                cambiarPassword(request)
            } else {
                Toast.makeText(this@RecuperacionActivity, "Contraseña no válida", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cambiarPassword(request: putRecuperarRequest) {
        recuperarService.actualizarPassword(request).enqueue(object : Callback<putRecuperarResponse> {
            override fun onResponse(call: Call<putRecuperarResponse>, response: Response<putRecuperarResponse>) {
                if (response.isSuccessful) {
                    val mensaje = response.body()?.mensaje ?: "Respuesta vacía del servidor"
                    // Manejar la respuesta exitosa
                    Toast.makeText(this@RecuperacionActivity, mensaje, Toast.LENGTH_SHORT).show()
                    val correo = binding.etcorre.text?.toString() ?: ""
                    val password = binding.etpalabra.text?.toString() ?: ""
                    val palabra = binding.etnuevcontra.text?.toString() ?: ""
                    // Redireccionar a LoginActivity
                    val intent = Intent(this@RecuperacionActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {

                    // Manejar errores de respuesta
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody ?: "Error en la respuesta del servidor"
                    Toast.makeText(this@RecuperacionActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    val correo = binding.etcorre.text?.toString() ?: ""
                    val password = binding.etpalabra.text?.toString() ?: ""
                    val palabra = binding.etnuevcontra.text?.toString() ?: ""
                }
            }

            override fun onFailure(call: Call<putRecuperarResponse>, t: Throwable) {
                // Manejar errores de comunicación

                val correo = binding.etcorre.text?.toString() ?: ""
                val password = binding.etpalabra.text?.toString() ?: ""
                val palabra = binding.etnuevcontra.text?.toString() ?: ""
            }
        })
    }

    private fun validatePassword(password: String): Boolean {
        val pattern = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,12}$"
        )
        return pattern.matcher(password).matches()
    }
}