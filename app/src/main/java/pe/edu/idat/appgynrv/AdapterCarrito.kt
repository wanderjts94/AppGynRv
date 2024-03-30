package pe.edu.idat.appgynrv

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.appgynrv.databinding.ItemcarritotiendaBinding

class AdapterCarrito(val listacarrito: List<Datoscarrito>,val context : Context)
    : RecyclerView.Adapter<AdapterCarrito.ViewHolder>() {

    inner class ViewHolder(val binding: ItemcarritotiendaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemcarritotiendaBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listacarrito.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listacarrito[position]) {
                binding.etnombreproductosc.text = nombreProduct
                binding.etprecio.text = precio
                binding.ivimagenC.setImageResource(img)
                binding.btnEdit.setOnClickListener(View.OnClickListener {
                    Toast.makeText(context, "se quiere consumir servicio put", Toast.LENGTH_LONG).show()
                })
            }
        }
    }
}

