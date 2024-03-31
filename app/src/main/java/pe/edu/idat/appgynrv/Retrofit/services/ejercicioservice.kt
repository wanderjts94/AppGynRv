package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.ejercicios.getlistaejercicioResponse
import retrofit2.Call
import retrofit2.http.GET

interface ejercicioservice {

    @GET("api/ejercicios")
    fun obtenerListaEjercicios(): Call<getlistaejercicioResponse>

}