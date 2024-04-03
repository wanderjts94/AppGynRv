package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.Tienda.Tienda
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface tiendaservice {

    @GET("/api/productos/{categoria}")
    fun obtenerListaProductos(@Path("categoria") categoria: String): Call<List<Tienda>>
}