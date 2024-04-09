package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.Config.postConfigRequest
import pe.edu.idat.appgynrv.Retrofit.models.Config.postConfigResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface configservice {

    @POST("configuracion/configuracion")
    fun crearConfiguracion(@Body request: postConfigRequest): Call<postConfigResponse>
}