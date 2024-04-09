package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.Detalle.detalleRequest
import pe.edu.idat.appgynrv.Retrofit.models.Detalle.detalleResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface detalleService {

    @POST("api/detalle/postDetail")
    fun registrarDetalle(@Body detalle: detalleRequest): Call<detalleResponse>
}