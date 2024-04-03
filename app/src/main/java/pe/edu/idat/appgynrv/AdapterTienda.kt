package pe.edu.idat.appgynrv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.appgynrv.Retrofit.models.Tienda.Tienda
import pe.edu.idat.appgynrv.databinding.ItemListatiendaBinding

class AdapterTienda(private var listaTienda: List<Tienda>, private val context: Context) :
    RecyclerView.Adapter<AdapterTienda.ViewHolder>() {

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
        val currentItem = listaTienda[position]
        with(holder.binding) {
            // Cargar imagen desde la carpeta drawable
            val resourceId = context.resources.getIdentifier(currentItem.imagen_producto, "drawable", context.packageName)
            if (resourceId != 0) {
                ivitemtienda.setImageResource(resourceId)
            } else {
                // Si no se encuentra la imagen, puedes establecer una imagen de respaldo o mostrar un mensaje de error
                ivitemtienda.setImageResource(R.drawable.carga)
            }
            tvnombresuple.text = currentItem.nom_producto
            tvpreciosuple.text = currentItem.prec_venta.toString()
            tvdescripcionsuple.text = currentItem.descripcion_producto
            btncomprarP.text = "Comprar"
            btncomprarP.setOnClickListener {
                val sharedPreferences =
                    context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                // Serialize data into a single string or use different keys for each piece of data
                val dataToSave = "${currentItem.imagen_producto},${currentItem.nom_producto},${currentItem.prec_venta},${currentItem.descripcion_producto}"
                editor.putString("guardardatos", dataToSave)
                editor.apply()

                // Obtener el NavController desde la vista
                val navController = it.findNavController()
                // Navegar al fragmento InfosaltoTFragment
                navController.navigate(R.id.detalleCompraFragment)
            }
        }
    }

    fun actualizarProductos(nuevaLista: List<Tienda>) {
        listaTienda = nuevaLista
        notifyDataSetChanged()
    }
}