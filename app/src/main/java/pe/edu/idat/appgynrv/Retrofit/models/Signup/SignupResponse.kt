package pe.edu.idat.appgynrv.Retrofit.models.Signup

import com.google.gson.annotations.SerializedName

data class SignupResponse (
    @SerializedName("message") val message: String,
    @SerializedName("objeto") val objeto: SignupRequest
)