package pe.edu.idat.appgynrv.Retrofit.models.Detalle

import com.google.gson.annotations.SerializedName

data class detalleRequest (
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("correo") val correo: String,
    @SerializedName("nomProducto") val nomProducto: String
)