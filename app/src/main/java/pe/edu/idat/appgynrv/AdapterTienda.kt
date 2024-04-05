package pe.edu.idat.appgynrv

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.appgynrv.Retrofit.models.Tienda.Tienda
import pe.edu.idat.appgynrv.databinding.ItemListatiendaBinding

class AdapterTienda(
    private var listaTienda: List<Tienda>,
    private val context: Context
) : RecyclerView.Adapter<AdapterTienda.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListatiendaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListatiendaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = listaTienda.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listaTienda[position]){
                binding.tvnombresuple.text = nom_producto
                binding.tvpreciosuple.text = prec_venta.toString()
                binding.tvdescripcionsuple.text = descripcion_producto
                binding.btncomprarP.text = "Comprar"

                val resourceId = context.resources.getIdentifier(
                    imagen_producto,
                    "drawable",
                    context.packageName
                )

                Log.i("Datos de imagen", imagen_producto)

                if (resourceId != 0) {
                    binding.ivitemtienda.setImageResource(resourceId)
                } else {
                    // Si no se encuentra la imagen, puedes establecer una imagen de respaldo o mostrar un mensaje de error
                    binding.ivitemtienda.setImageResource(R.drawable.carga)
                }

                Glide.with(context)
                    .load(if (resourceId != 0) resourceId else R.drawable.carga) // Usar el icono por defecto si no se encuentra la imagen
                    .into(binding.ivitemtienda)

                binding.btncomprarP.setOnClickListener {
                    val sharedPreferences =
                        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    // Serialize data into a single string or use different keys for each piece of data
                    val dataToSave =
                        "${imagen_producto},${nom_producto},${prec_venta},${descripcion_producto}"
                    Log.i("Valores de dataToSave", dataToSave)
                    editor.putString("guardardatos", dataToSave)
                    editor.apply()

                    // Obtener el NavController desde la vista
                    val navController = it.findNavController()
                    // Navegar al fragmento InfosaltoTFragment
                    navController.navigate(R.id.detalleCompraFragment)
                }
            }
        }
    }

    fun actualizarProductos(nuevaLista: List<Tienda>) {
        listaTienda = nuevaLista
        notifyDataSetChanged()
    }
}