package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.Recuperacion.putRecuperarRequest
import pe.edu.idat.appgynrv.Retrofit.models.Recuperacion.putRecuperarResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT


interface recuperarservice {
    @PUT("actualizarpassword")
    fun actualizarPassword(@Body request: putRecuperarRequest): Call<putRecuperarResponse>

}