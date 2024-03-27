package pe.edu.idat.appgynrv.Retrofit.services

import pe.edu.idat.appgynrv.Retrofit.models.Login.LoginRequest
import pe.edu.idat.appgynrv.Retrofit.models.Login.LoginResponse
import pe.edu.idat.appgynrv.Retrofit.models.Signup.SignupRequest
import pe.edu.idat.appgynrv.Retrofit.models.Signup.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface loginservice {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("signup")
    fun signup(@Body request: SignupRequest): Call<SignupResponse>
}