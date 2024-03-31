package pe.edu.idat.appgynrv

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.bumptech.glide.Glide
import pe.edu.idat.appgynrv.Retrofit.models.ejercicios.Ejercicio
import pe.edu.idat.appgynrv.databinding.ItemejerciciosderutinaBinding

class AdapterNivelRutina(
    private var listaEjercicios: List<Ejercicio>,
    private val context: Context
) : RecyclerView.Adapter<AdapterNivelRutina.ViewHolder>() {

    inner class ViewHolder(val binding: ItemejerciciosderutinaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemejerciciosderutinaBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listaEjercicios.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listaEjercicios[position]) {
                binding.etnameejercicioQ.text = nombreEjercicio
                binding.etcantejercicio.text = descripcionEjercicio
                // Obtener el identificador del recurso drawable
                val resourceId = context.resources.getIdentifier(nombreImagen, "drawable", context.packageName)

                // Cargar la imagen utilizando Glide
                Glide.with(context)
                    .load(if (resourceId != 0) resourceId else R.drawable.carga) // Usar el icono por defecto si no se encuentra la imagen
                    .into(binding.imageView)
                binding.btninfoC.setOnClickListener {

                    // Obtener el NavController desde la vista
                    val navController = it.findNavController()
                    // Navegar al fragmento InfosaltoTFragment
                    navController.navigate(R.id.infosaltoTFragment)

                }
                binding.cvitemejer.setOnClickListener {
                    Toast.makeText(context, "Haz clic en iniciar rutina", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun actualizarLista(nuevaLista: List<Ejercicio>) {
        listaEjercicios = nuevaLista
        notifyDataSetChanged()
    }
    class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View,
            parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.bottom = verticalSpaceHeight
        }
    }
}