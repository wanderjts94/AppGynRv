package pe.edu.idat.appgynrv.Retrofit.models.Signup

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("correo") val correo: String,
    @SerializedName("nombreUsuario") val nombreUsuario: String,
    @SerializedName("apellidoUsuario") val apellidoUsuario: String,
    @SerializedName("password") val password: String,
    @SerializedName("palabraClave") val palabraClave: String,
    @SerializedName("dni") val dni: String,
    @SerializedName("numCelular") val numCelular: String,
    @SerializedName("altura") val altura: Double,
    @SerializedName("peso") val peso: Double,
    @SerializedName("fechaRegistro") val fechaRegistro: String,
    @SerializedName("fechaNacimiento") val fechaNacimiento: String,
)
/*
  "correo": "josephhuamani@example.com",
  "nombreUsuario": "Joseph Manuel",
  "apellidoUsuario": "Huamani Yalli",
  "password": "73847",
  "palabraClave": "clave",
  "dni": "78394857",
  "numCelular": "947123456",
  "altura": 1.76,
  "peso": 70,
  "fechaRegistro": "2024-03-24",
  "fechaNacimiento": "2005-05-25",
  "tipoUsuario": "Usuario"
 */