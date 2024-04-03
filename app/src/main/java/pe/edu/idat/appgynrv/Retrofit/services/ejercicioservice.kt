package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.ejercicios.Ejercicio
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ejercicioservice {

    @GET("api/ejercicios/{nivel}")
    fun obtenerListaEjercicios(@Path("nivel") nivel: String): Call<List<Ejercicio>>

}