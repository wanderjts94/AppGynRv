package pe.edu.idat.appgynrv.Retrofit.models

import com.google.gson.annotations.SerializedName

data class putperfilRequest(
    @SerializedName("nombreUsuario") val nombreUsuario: String,
    @SerializedName("apellidoUsuario") val apellidoUsuario: String,
    @SerializedName("dni") val dni: String,
    @SerializedName("numCelular") val numCelular: String,
    @SerializedName("altura") val altura: Double,
    @SerializedName("peso") val peso: Double,
    @SerializedName("fechaNacimiento") val fechaNacimiento: String
)
