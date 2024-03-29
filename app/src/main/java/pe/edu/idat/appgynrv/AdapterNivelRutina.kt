package pe.edu.idat.appgynrv

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import pe.edu.idat.appgynrv.databinding.ItemejerciciosderutinaBinding

class AdapterNivelRutina (val listanivelrutinas: List<NivelRutinas>,val context :Context)
    :RecyclerView.Adapter<AdapterNivelRutina.ViewHolder>() {

    inner class ViewHolder(val binding: ItemejerciciosderutinaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemejerciciosderutinaBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listanivelrutinas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listanivelrutinas[position]) {
                binding.etnameejercicioQ.text = nombreejer
                binding.etcantejercicio.text = cantidadejer
                binding.imageView.setImageResource(img)
                binding.btninfoC.setOnClickListener(View.OnClickListener {
                    Toast.makeText(context, "se redireccionara a info", Toast.LENGTH_LONG).show()
                })
                binding.cvitemejer.setOnClickListener(View.OnClickListener {
                    Toast.makeText(context, "se redireccionara a subinterfaz", Toast.LENGTH_LONG).show()
                })
            }
        }
    }
}

class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight
    }
}