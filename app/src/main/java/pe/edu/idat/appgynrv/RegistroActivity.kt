package pe.edu.idat.appgynrv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import pe.edu.idat.appgynrv.Retrofit.models.Signup.SignupRequest
import pe.edu.idat.appgynrv.Retrofit.models.Signup.SignupResponse
import pe.edu.idat.appgynrv.Retrofit.services.loginservice
import pe.edu.idat.appgynrv.databinding.ActivityRegistroBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val baseUrl = "http://192.168.18.28:9090/api/usuarios/"

        Log.i("Url de la api","Esta es la url de la API: " + baseUrl)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(loginservice::class.java)

        // Set click listener for tvlregistro
        binding.tvllogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Obtener la fecha actual
        val fechaActual = Date()

        // Definir el formato deseado
        val formato = SimpleDateFormat("yyyy-MM-dd")

        // Formatear la fecha
        val fechaActualFormateada = formato.format(fechaActual)


        binding.btnRegistrar.setOnClickListener {
            val nombre = binding.rptNombres.text.toString()
            val apellidos = binding.rptApellidos.text.toString()
            val correo = binding.rptCorreo.text.toString()
            val password = binding.rptPassword.text.toString()
            val palabraClave = binding.rptPalabraClave.text.toString()
            val dni = binding.rptDNI.text.toString()
            val celular = binding.rptCelular.text.toString()
            val fechaNac = binding.rptFechaNac.text.toString()
            val peso = binding.rptPeso.text.toString()
            val altura = binding.rptAltura.text.toString()

            val request = SignupRequest(correo,nombre,apellidos,password,palabraClave,dni,celular,altura.toDouble(),peso.toDouble(),fechaActualFormateada,fechaNac)
            Log.i("Datos de request",request.toString())
            service.signup(request).enqueue(object : Callback<SignupResponse> {
                override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                    if (response.isSuccessful) {
                        val mensaje = response.body()?.message ?: "Respuesta vacía del servidor"
                        Toast.makeText(this@RegistroActivity, mensaje, Toast.LENGTH_SHORT).show()
                        // Redireccionar a MainActivity
                        val intent = Intent(this@RegistroActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@RegistroActivity, "Las Credenciales son Invalidas", Toast.LENGTH_SHORT).show()
                        clearTEXT()
                    }
                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    Toast.makeText(this@RegistroActivity, "Error en la comunicación con el servidor"
                        + t.message, Toast.LENGTH_SHORT).show()
                    Log.i("Error en el servidor",t.stackTraceToString())
                    // Limpiar los cuadros de texto
                    clearTEXT()
                }
            })
        }
    }

    private fun clearTEXT(){

        val nombres = binding.rptNombres.text?.toString() ?: ""
        val apellidos = binding.rptApellidos.text?.toString() ?: ""
        val correo = binding.rptCorreo.text?.toString() ?: ""
        val contrasena = binding.rptPassword.text?.toString() ?: ""
        val palabraclave = binding.rptPalabraClave.text?.toString() ?: ""
        val dni = binding.rptDNI.text?.toString() ?: ""
        val celular = binding.rptCelular.text?.toString() ?: ""
        val fechanac = binding.rptFechaNac.text?.toString() ?: ""
        val peso = binding.rptPeso.text?.toString() ?: ""
        val altura = binding.rptAltura.text?.toString() ?: ""

    }
}