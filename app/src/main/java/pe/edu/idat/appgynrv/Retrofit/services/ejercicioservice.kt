package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.ejercicios.getlistaejercicioResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ejercicioservice {

    @GET("{nivel}")
    fun obtenerListaEjercicios(@Path("nivel") nivel: String): Call<getlistaejercicioResponse>

}