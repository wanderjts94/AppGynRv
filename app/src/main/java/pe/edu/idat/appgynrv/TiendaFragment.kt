package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import pe.edu.idat.appgynrv.databinding.FragmentTiendaBinding

class TiendaFragment : Fragment() {
    private var _binding: FragmentTiendaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTiendaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar adaptadores y datos para RecyclerView de productos
        val listaItemProductos = obtenerItemProductos()
        val adapterProductos = AdapterTienda(listaItemProductos, requireContext())
        binding.rvProductos.layoutManager = GridLayoutManager(context, 2)
        binding.rvProductos.adapter = adapterProductos

        // Configurar adaptadores y datos para RecyclerView de entrenamiento
        val listaItemEntrenamiento = obtenerItemEntrenamiento()
        val adapterEntrenamiento = AdapterTienda(listaItemEntrenamiento, requireContext())
        binding.rvEntrenamiento.layoutManager = GridLayoutManager(context, 2)
        binding.rvEntrenamiento.adapter = adapterEntrenamiento

        // Configurar adaptadores y datos para RecyclerView de productos
        val listaItemOtros = obtenerItemOtros()
        val adapterOtros = AdapterTienda(listaItemOtros, requireContext())
        binding.rvOtros.layoutManager = GridLayoutManager(context, 2)
        binding.rvOtros.adapter = adapterOtros

        // Manejar clics en botones para cambiar visibilidad de RecyclerViews
        binding.btnsuplemento.setOnClickListener {
            binding.rvProductos.visibility = View.VISIBLE
            binding.rvOtros.visibility = View.GONE
            binding.rvEntrenamiento.visibility = View.GONE
        }

        binding.btnequipoentre.setOnClickListener {
            binding.rvProductos.visibility = View.GONE
            binding.rvOtros.visibility = View.GONE
            binding.rvEntrenamiento.visibility = View.VISIBLE
        }

        binding.btnotros.setOnClickListener {
            binding.rvProductos.visibility = View.GONE
            binding.rvEntrenamiento.visibility = View.GONE
            binding.rvOtros.visibility = View.VISIBLE
        }
    }

    private fun obtenerItemProductos(): List<Tienda> {
        val listaItemProductos = ArrayList<Tienda>()
        listaItemProductos.add(Tienda(R.drawable.vitaminas,"Producto:Vitamina Embace ","Precio:20","vitamina para agarrar masa"))
        listaItemProductos.add(Tienda(R.drawable.vitaminas2,"Producto:Vitamina Embace ","Precio:15","vitamina para agarrar masa"))
        listaItemProductos.add(Tienda(R.drawable.vitaminas3,"Producto:Vitamina Embace ","Precio:20","vitamina para agarrar masa"))
        return listaItemProductos
    }

    private fun obtenerItemEntrenamiento(): List<Tienda> {
        val listaItemEntrenamiento = ArrayList<Tienda>()
        listaItemEntrenamiento.add(Tienda(R.drawable.mancuernas,"Producto: Mancuernas 5kg","Precio:15","Instrumento para aumentar fuerza"))
        listaItemEntrenamiento.add(Tienda(R.drawable.maquina1,"Producto:Home GYM ","Precio:499","GYM en casa"))
        listaItemEntrenamiento.add(Tienda(R.drawable.maquina2,"Producto:Abdominales Home ","Precio:15","Maquinaria para trabajar abdominales"))
        return listaItemEntrenamiento
    }

    private fun obtenerItemOtros(): List<Tienda> {
        val listaItemOtros = ArrayList<Tienda>()
        listaItemOtros.add(Tienda(R.drawable.sporade1,"Producto: Mancuernas 5kg","Precio:15","Instrumento para aumentar fuerza"))
        listaItemOtros.add(Tienda(R.drawable.sporade2,"Producto:Home GYM ","Precio:499","GYM en casa"))
        listaItemOtros.add(Tienda(R.drawable.sporade3,"Producto:Abdominales Home ","Precio:15","Maquinaria para trabajar abdominales"))
        return listaItemOtros
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
