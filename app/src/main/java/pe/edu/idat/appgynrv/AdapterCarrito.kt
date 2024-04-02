package pe.edu.idat.appgynrv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.appgynrv.databinding.ItemcarritotiendaBinding

class AdapterCarrito(val context: Context) : RecyclerView.Adapter<AdapterCarrito.ViewHolder>() {
    private var listaDeProductos = ArrayList<Datoscarrito>()

    inner class ViewHolder(val binding: ItemcarritotiendaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemcarritotiendaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listaDeProductos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listaDeProductos[position]) {
                binding.etnombreproductosc.text = nombreProduct
                binding.etprecio.text = precio
                binding.etnumerocant.text = cantidad
                binding.ivimagenC.setImageResource(img)
                // Aquí puedes agregar cualquier otra configuración necesaria para los elementos de la lista
            }
        }
    }

    // Método para actualizar la lista de productos en el adaptador
    fun actualizarProductos(nuevaLista: List<Datoscarrito>) {
        listaDeProductos.clear()
        listaDeProductos.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}