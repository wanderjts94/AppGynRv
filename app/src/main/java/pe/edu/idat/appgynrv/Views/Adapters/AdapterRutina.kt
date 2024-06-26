package pe.edu.idat.appgynrv.Views.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.Views.Rutina.Rutina
import pe.edu.idat.appgynrv.databinding.ItemListaejerciciosBinding

class AdapterRutina(val listarutinas:List<Rutina>, val context:Context)
    :RecyclerView.Adapter<AdapterRutina.ViewHolder>() {
    inner class ViewHolder(val binding: ItemListaejerciciosBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListaejerciciosBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listarutinas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(listarutinas[position]) {
                binding.tvnombrejer.text = nombre
                binding.ivejercicio.setImageResource(img)
                binding.cvejercicios.setOnClickListener {
                    //ALMACENAR NIVEL DE RUTINA
                    val sharedPreferences =
                        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("nombre", nombre) // Almacena el nombre
                    editor.apply()
                    //REDIRECCION A RUTINAPORNIVELFRAGMENT
                    val navController = it.findNavController()
                    navController.navigate(R.id.rutinapornivelFragment)
                }
            }
        }
    }
}