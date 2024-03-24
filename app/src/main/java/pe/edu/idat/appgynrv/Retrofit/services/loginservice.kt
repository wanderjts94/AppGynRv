package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.LoginRequest
import pe.edu.idat.appgynrv.Retrofit.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface loginservice {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}