package pe.edu.idat.appgynrv.Retrofit.models

import com.google.gson.annotations.SerializedName

data class putRecuperarRequest(
    @SerializedName("correo") val correo: String,
    @SerializedName("password") val password: String,
    @SerializedName("palabraClave") val palabraClave: String
)
