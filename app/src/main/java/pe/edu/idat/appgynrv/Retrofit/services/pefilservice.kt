package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.getperfilResponse
import pe.edu.idat.appgynrv.Retrofit.models.putperfilRequest
import pe.edu.idat.appgynrv.Retrofit.models.putperfilResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface pefilservice {
    @GET("email/{correo}")
    fun obtenerPerfilPorCorreo(@Path("correo") correo: String): Call<getperfilResponse>

    @PUT("{correo}")
    fun actualizarPerfil(@Path("correo") correo: String, @Body request: putperfilRequest): Call<putperfilResponse>
}