package pe.edu.idat.appgynrv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.idat.appgynrv.Retrofit.models.Perfil.getperfilResponse
import pe.edu.idat.appgynrv.Retrofit.models.Perfil.putperfilRequest
import pe.edu.idat.appgynrv.Retrofit.models.Perfil.putperfilResponse
import pe.edu.idat.appgynrv.Retrofit.services.pefilservice
import pe.edu.idat.appgynrv.databinding.FragmentPerfilBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PerfilFragment : Fragment() {
    private lateinit var binding: FragmentPerfilBinding
    private val perfilService: pefilservice

    init {
        // Configuración de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.10:9090/api/usuarios/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        perfilService = retrofit.create(pefilservice::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento usando el objeto LayoutInflater
        binding = FragmentPerfilBinding.inflate(inflater, container, false)

        // Obtener el correo electrónico almacenado en SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val correo = sharedPreferences.getString("correo", "")
        Log.i("Correo Cargado en Perfil", correo.toString())

        // Si hay un correo electrónico almacenado, cargar automáticamente los datos del perfil
        if (!correo.isNullOrEmpty()) {
            obtenerPerfilPorCorreo(correo)
            Log.i("Perfil Obtenido por Correo",obtenerPerfilPorCorreo(correo).toString())
        }

        // Configurar el OnClickListener para el botón de guardar
        binding.btnGuardar.setOnClickListener {
            val correo = binding.etCorreo.text.toString()
            actualizarPerfil(correo)
        }

        // Devolver la vista raíz del fragmento
        return binding.root
    }


    private fun obtenerPerfilPorCorreo(correo: String) {
        val call = perfilService.obtenerPerfilPorCorreo(correo)
        call.enqueue(object : Callback<getperfilResponse> {
            override fun onResponse(call: Call<getperfilResponse>, response: Response<getperfilResponse>) {
                if (response.isSuccessful) {
                    val perfil = response.body()
                    perfil?.let {
                        actualizarDatosPerfil(it)
                        Toast.makeText(requireContext(), "Datos de usuario obtenidos", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al obtener perfil", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<getperfilResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun actualizarDatosPerfil(perfil: getperfilResponse) {
        binding.etCorreo.setText(perfil.correo)
        binding.etNombre.setText(perfil.nombreUsuario)
        binding.etApellido.setText(perfil.apellidoUsuario)
        binding.etDNI.setText(perfil.dni)
        binding.etcelular.setText(perfil.numCelular)
        binding.etaltura.setText(perfil.altura.toString())
        binding.etPes.setText(perfil.peso.toString())
        binding.etFecha.setText(perfil.fechaNacimiento)
        binding.etpalabrac.setText(perfil.palabraClave)
    }

    private fun actualizarPerfil(correo: String) {
        val nombre = binding.etNombre.text.toString()
        val apellido = binding.etApellido.text.toString()
        val dni = binding.etDNI.text.toString()
        val celular = binding.etcelular.text.toString()
        val altura = binding.etaltura.text.toString().toDouble()
        val peso = binding.etPes.text.toString().toDouble()
        val fechaNacimiento = binding.etFecha.text.toString()
        val palabraClave = binding.etpalabrac.text.toString()

        val request = putperfilRequest(nombre, apellido, dni, celular, altura, peso, fechaNacimiento, palabraClave)

        val call = perfilService.actualizarPerfil(correo, request)
        call.enqueue(object : Callback<putperfilResponse> {
            override fun onResponse(call: Call<putperfilResponse>, response: Response<putperfilResponse>) {
                if (response.isSuccessful) {
                    val mensaje = response.body()?.mensajePerfil
                    Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Error al actualizar perfil", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<putperfilResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}