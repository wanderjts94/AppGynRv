package pe.edu.idat.appgynrv.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import pe.edu.idat.appgynrv.R
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
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var correoTextInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        correoTextInputLayout = findViewById(R.id.textInputLayoutCorreoE)

        val baseUrl = "http://192.168.1.43:9090/api/usuarios/"

        Log.i("Url de la api", "Esta es la url de la API: $baseUrl")

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

            // Verificar si hay errores en el TextInputLayout del correo electrónico
            if (validateEmail(correo) && correoTextInputLayout.error.isNullOrEmpty()) {
                // Validar contraseña
                if (!validatePassword(password)) {
                    // Mostrar mensaje de error para contraseña no válida
                    Toast.makeText(this@RegistroActivity, "Contraseña no válida", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val request = SignupRequest(correo, nombre, apellidos, password, palabraClave, dni, celular, altura.toDouble(), peso.toDouble(), fechaActualFormateada, fechaNac)
                Log.i("Datos de request", request.toString())
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
                        Toast.makeText(this@RegistroActivity, "Error en la comunicación con el servidor" + t.message, Toast.LENGTH_SHORT).show()
                        Log.i("Error en el servidor", t.stackTraceToString())
                        // Limpiar los cuadros de texto
                        clearTEXT()
                    }
                })
            } else {
                Toast.makeText(this@RegistroActivity, "Por favor, corrige los errores antes de continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearTEXT() {
        binding.rptNombres.text = null
        binding.rptApellidos.text = null
        binding.rptCorreo.text = null
        binding.rptPassword.text = null
        binding.rptPalabraClave.text = null
        binding.rptDNI.text = null
        binding.rptCelular.text = null
        binding.rptFechaNac.text = null
        binding.rptPeso.text = null
        binding.rptAltura.text = null
    }

    private fun validateEmail(email: String): Boolean {
        val pattern = Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" +
                    "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
                    "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
                    "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" +
                    "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        )
        return pattern.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        val pattern = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,12}$"
        )
        return pattern.matcher(password).matches()
    }
}