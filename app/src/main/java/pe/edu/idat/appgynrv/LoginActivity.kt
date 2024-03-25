package pe.edu.idat.appgynrv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.idat.appgynrv.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import pe.edu.idat.appgynrv.Retrofit.models.LoginRequest
import pe.edu.idat.appgynrv.Retrofit.models.LoginResponse
import pe.edu.idat.appgynrv.Retrofit.services.loginservice
import java.net.Inet4Address
import java.net.NetworkInterface

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val baseUrl = "http://192.168.1.40:9090/api/usuarios/"

        Log.i("Url de la api","Esta es la url de la API: " + baseUrl)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(loginservice::class.java)

        binding.btnlogin.setOnClickListener {
            val correo = binding.etcorreo.text.toString()
            val password = binding.etpassword.text.toString()

            val request = LoginRequest(correo, password)

            service.login(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val mensaje = response.body()?.mensaje ?: "Respuesta vacía del servidor"
                        Toast.makeText(this@LoginActivity, mensaje, Toast.LENGTH_SHORT).show()
                        // Redireccionar a MainActivity
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Las Credenciales son Invalidas", Toast.LENGTH_SHORT).show()
                        binding.etcorreo.text.clear()
                        binding.etpassword.text.clear()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error en la comunicación con el servidor"
                            + t.message, Toast.LENGTH_SHORT).show()
                    Log.i("Error en el servidor",t.stackTraceToString())

                    binding.etcorreo.text.clear()
                    binding.etpassword.text.clear()
                }
            })
        }
    }
}
