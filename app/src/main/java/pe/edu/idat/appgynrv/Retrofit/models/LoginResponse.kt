package pe.edu.idat.appgynrv.Retrofit.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("mensaje") val mensaje: String
)
