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
                binding.etnumerocant.setText(cantidad)
                binding.ivimagenC.setImageResource(img)
                // Aquí puedes agregar cualquier otra configuración necesaria para los elementos de la lista
            }

            // Agregar OnClickListener al botón de borrar
            binding.btnborrarC.setOnClickListener {
                // Obtener la posición del elemento que se va a eliminar
                val itemPosition = adapterPosition
                // Verificar si la posición es válida
                if (itemPosition != RecyclerView.NO_POSITION) {
                    // Eliminar el elemento de la lista
                    listaDeProductos.removeAt(itemPosition)
                    // Notificar al adaptador que se eliminó un elemento en la posición específica
                    notifyItemRemoved(itemPosition)

                    // Obtener SharedPreferences
                    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()

                    // Obtener los datos actuales del carrito
                    val datosCarritoActual = sharedPreferences.getString("datosparacarrito", "")

                    // Si hay datos en el carrito
                    if (!datosCarritoActual.isNullOrEmpty()) {
                        // Separar los productos por punto y coma (;)
                        val productos = datosCarritoActual.split(";").toMutableList()
                        // Eliminar el producto correspondiente a la posición del RecyclerView
                        productos.removeAt(itemPosition)
                        // Reconstruir los datos del carrito con los productos restantes
                        val nuevosDatos = productos.joinToString(separator = ";")
                        // Actualizar SharedPreferences con los nuevos datos del carrito
                        editor.putString("datosparacarrito", nuevosDatos)
                        editor.apply()
                    }
                }
            }

            binding.btnEdit.setOnClickListener {
                // Obtener la posición del elemento que se va a editar
                val itemPosition = adapterPosition
                // Verificar si la posición es válida
                if (itemPosition != RecyclerView.NO_POSITION) {
                    // Obtener la cantidad ingresada por el usuario desde el EditText
                    val nuevaCantidad = binding.etnumerocant.text.toString()
                    // Actualizar la cantidad del producto en la lista de productos
                    listaDeProductos[itemPosition].cantidad = nuevaCantidad
                    // Notificar al adaptador que los datos han cambiado en la posición específica
                    notifyItemChanged(itemPosition)

                    // Obtener SharedPreferences
                    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()

                    // Obtener los datos actuales del carrito
                    val datosCarritoActual = sharedPreferences.getString("datosparacarrito", "")

                    // Si hay datos en el carrito
                    if (!datosCarritoActual.isNullOrEmpty()) {
                        // Separar los productos por punto y coma (;)
                        val productos = datosCarritoActual.split(";").toMutableList()
                        // Actualizar la cantidad del producto correspondiente en los datos del carrito
                        val productoActualizado = "${listaDeProductos[itemPosition].img},${listaDeProductos[itemPosition].nombreProduct},${listaDeProductos[itemPosition].precio},$nuevaCantidad"
                        productos[itemPosition] = productoActualizado
                        // Reconstruir los datos del carrito con los productos actualizados
                        val nuevosDatos = productos.joinToString(separator = ";")
                        // Actualizar SharedPreferences con los nuevos datos del carrito
                        editor.putString("datosparacarrito", nuevosDatos)
                        editor.apply()
                    }
                }
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