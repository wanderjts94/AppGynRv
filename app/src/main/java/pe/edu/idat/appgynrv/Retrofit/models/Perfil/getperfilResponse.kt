package pe.edu.idat.appgynrv.Retrofit.models.Perfil

data class getperfilResponse(
    val correo: String,
    val nombreUsuario: String,
    val apellidoUsuario: String,
    val dni: String,
    val numCelular: String,
    val altura: Double,
    val peso: Double,
    val fechaNacimiento: String,
    val palabraClave: String
)
