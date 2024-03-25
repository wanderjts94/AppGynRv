package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.putRecuperarRequest
import pe.edu.idat.appgynrv.Retrofit.models.putRecuperarResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT


interface recuperarservice {
    @PUT("actualizarpassword")
    fun actualizarPassword(@Body request: putRecuperarRequest): Call<putRecuperarResponse>

}