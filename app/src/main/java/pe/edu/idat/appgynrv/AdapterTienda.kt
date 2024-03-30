package pe.edu.idat.appgynrv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.appgynrv.databinding.ItemListatiendaBinding

class AdapterTienda(val listaTienda:List<Tienda>,val context :Context)
    :RecyclerView.Adapter<AdapterTienda.ViewHolder>() {
    inner class ViewHolder(val binding: ItemListatiendaBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemListatiendaBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount()=listaTienda.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(listaTienda[position]){
                binding.ivitemtienda.setImageResource(img)
                binding.tvnombresuple.text = nombre
                binding.tvpreciosuple.text = precio
                binding.tvdescripcionsuple.text = descripcion
                binding.btncomprarsuple.text="Comprar"
            }
        }
    }
}