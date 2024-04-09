package pe.edu.idat.appgynrv.Retrofit.models.Config

import com.google.gson.annotations.SerializedName

data class postConfigRequest (
    @SerializedName("diasEntrenamiento") val diasEntrenamiento: Int,
    @SerializedName("diaInicioEntrenamiento") val diaInicioEntrenamiento: String
)