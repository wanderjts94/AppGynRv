package pe.edu.idat.appgynrv.Retrofit.models.Recuperacion

import com.google.gson.annotations.SerializedName

data class putRecuperarRequest(
    @SerializedName("correo") val correo: String,
    @SerializedName("password") val password: String,
    @SerializedName("palabraClave") val palabraClave: String
)
