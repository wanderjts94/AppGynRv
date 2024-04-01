package pe.edu.idat.appgynrv
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.appgynrv.databinding.ItemListatiendaBinding

class AdapterTienda(private val listaTienda: List<Tienda>, private val context: Context) :
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
            ivitemtienda.setImageResource(currentItem.img)
            tvnombresuple.text = currentItem.nombre
            tvpreciosuple.text = currentItem.precio
            tvdescripcionsuple.text = currentItem.descripcion
            btncomprarP.text = "Comprar"
            btncomprarP.setOnClickListener {
                val sharedPreferences =
                    context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                // Serialize data into a single string or use different keys for each piece of data
                val dataToSave = "${currentItem.img},${currentItem.nombre},${currentItem.precio},${currentItem.descripcion}"
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